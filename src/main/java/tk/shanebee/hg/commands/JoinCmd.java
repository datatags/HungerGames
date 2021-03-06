package tk.shanebee.hg.commands;

import tk.shanebee.hg.game.Game;
import tk.shanebee.hg.util.Util;

public class JoinCmd extends BaseCmd {

    public JoinCmd() {
        forcePlayer = true;
        cmdName = "join";
        forceInGame = false;
        argLength = 2;
        usage = "<arena-name>";
    }

    @Override
    public boolean run() {
        Game g = gameManager.getGame(args[1]);
        if (g != null) {
            g.preJoin(player);
        } else {
            Util.scm(player, lang.cmd_delete_noexist);
        }
        return true;
    }

}
