package tk.shanebee.hg.tasks;

import org.bukkit.Bukkit;

import tk.shanebee.hg.Game;
import tk.shanebee.hg.HG;
import tk.shanebee.hg.Util;

public class StartingTask implements Runnable {

	private int timer;
	private int id;
	private Game game;

	public StartingTask(Game g) {
		this.timer = 30;
		this.game = g;
		Util.broadcast(HG.lang.game_started.replace("<arena>", g.getName()));
		Util.broadcast(HG.lang.game_join.replace("<arena>", g.getName()));

		this.id = Bukkit.getScheduler().scheduleSyncRepeatingTask(HG.plugin, this, 5 * 20L, 5 * 20L);
	}

	@Override
	public void run() {
		timer = (timer - 5);

		if (timer <= 0) {
			stop();
			game.startFreeRoam();
		} else {
			game.msgAll(HG.lang.game_countdown.replace("<timer>", String.valueOf(timer)));
		}
	}

	public void stop() {
		Bukkit.getScheduler().cancelTask(id);
	}
}