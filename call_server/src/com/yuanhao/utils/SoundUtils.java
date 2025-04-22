package com.yuanhao.utils;

import java.io.FileInputStream;
import java.io.IOException;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class SoundUtils {
	public static synchronized void playSound(String ss, String winF)
			throws IOException, InterruptedException {
		// System.out.println("1");
		FileInputStream fileau;
		fileau = new FileInputStream("call_server/src/com/yuanhao/utils/sound/please.wav");
		AudioStream as = new AudioStream(fileau);
		AudioPlayer.player.start(as);
		Thread.sleep(500);

		for (int i = 0; i < ss.length(); i++) {
			fileau = new FileInputStream("call_server/src/com/yuanhao/utils/sound/" + ss.charAt(i) + ".wav");
			as = new AudioStream(fileau);
			AudioPlayer.player.start(as);
			Thread.sleep(500);
		}

		fileau = new FileInputStream("call_server/src/com/yuanhao/utils/sound/dao.wav");
		as = new AudioStream(fileau);
		AudioPlayer.player.start(as);
		Thread.sleep(500);

		for (int i = 0; i < winF.length(); i++) {
			fileau = new FileInputStream("call_server/src/com/yuanhao/utils/sound/" + winF.charAt(i) + ".wav");
			as = new AudioStream(fileau);
			AudioPlayer.player.start(as);
			Thread.sleep(500);
		}

		fileau = new FileInputStream("call_server/src/com/yuanhao/utils/sound/last.wav");
		as = new AudioStream(fileau);
		AudioPlayer.player.start(as);
		Thread.sleep(2000);
	}

	public static void main(String[] args) {

		try {
			SoundUtils.playSound("A2015", "8");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
