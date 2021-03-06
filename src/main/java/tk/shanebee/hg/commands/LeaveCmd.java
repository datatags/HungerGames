package tk.shanebee.hg.commands;

import tk.shanebee.hg.*;
import tk.shanebee.hg.data.Config;
import tk.shanebee.hg.game.Game;
import tk.shanebee.hg.util.Util;
import tk.shanebee.hg.util.Vault;

public class LeaveCmd extends BaseCmd {

	public LeaveCmd() {
		forcePlayer = true;
		cmdName = "leave";
		forceInGame = false;
		argLength = 1;
	}

	@Override
	public boolean run() {
        String prefix = lang.prefix;
		Game game = playerManager.getGame(player);
		if (game != null) {
            if (Config.economy) {
                Status status = game.getStatus();
                if ((status == Status.WAITING || status == Status.COUNTDOWN) && game.getCost() > 0) {
                    Vault.economy.depositPlayer(player, game.getCost());
                    Util.scm(player, prefix +
                            lang.cmd_leave_refund.replace("<cost>", String.valueOf(game.getCost())));
                }
            }
            if (playerManager.hasSpectatorData(player)) {
                game.leaveSpectate(player);
            } else {
                game.leave(player, false);
            }
            Util.scm(player, prefix + lang.cmd_leave_left.replace("<arena>", game.getName()));
        } else {
		    Util.scm(player, prefix + lang.cmd_base_nogame);
        }
		return true;
	}
}