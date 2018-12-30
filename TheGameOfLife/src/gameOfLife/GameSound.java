package gameOfLife;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameSound {
	
	/**
	 * Instance Variables
	 */
	private String audioFilePath;
	private Clip audioClip;
	public static boolean isMuted;
	
	/**
	 * Static sounds to be used within the game
	 */
	public static GameSound backGroundThemeOne = new GameSound("oceanselevensountrack.wav");
	public static GameSound clickSound = new GameSound("button-30.wav");
	public static GameSound clappingSound = new GameSound("clapping.wav");
	public static GameSound barkSound = new GameSound("barkSound.wav");
	public static GameSound carSound = new GameSound("carSound.wav");
	public static GameSound cashSound = new GameSound("cashSound.wav");
	public static GameSound childrenSound = new GameSound("childrenSound.wav");
	public static GameSound crySound = new GameSound("crySound.wav");
	public static GameSound doorBellSound = new GameSound("doorBellSound.wav");
	public static GameSound fineSound = new GameSound("fineSound.wav");
	public static GameSound meowSound = new GameSound("meowSound.wav");
	public static GameSound policeSound = new GameSound("policeSound.wav");
	public static GameSound shuffleSound = new GameSound("shuffleSound.wav");
	public static GameSound weddingSound = new GameSound("weddingSound.wav");
	
	/**
	 * Constructor for GameSound objects
	 * @param audioFilePath the string path of the .wav file to use as the sound
	 * @author Alex Crowley 40121793
	 */
	public GameSound(String audioFilePath)
	{
		this.audioFilePath = audioFilePath;
	}
	
	/**
	 * If the sound is muted we do not play the sound, otherwise we call playOrResumeSound
	 * @author Alex Crowley 40121793
	 */
	public void play()
	{
		if(!isMuted)
		{
			playOrResumeSound();
		}
	}
	
	/**
	 * Loads the sound ready to be played and then begins playing the sound
	 * @author Alex Crowley 40121793
	 */
	private void playOrResumeSound()
	{
		File audioFile = new File(getClass().getResource(audioFilePath).getFile());
		
        try 
        {
        	AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        	 
            AudioFormat format = audioStream.getFormat();
  
            DataLine.Info info = new DataLine.Info(Clip.class, format);
        
            Clip audioClip = (Clip) AudioSystem.getLine(info);
  
            this.audioClip = audioClip;
        	
        	audioClip.open(audioStream);
        	
        	audioClip.start();
          
		} 
        catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) 
        {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method pauses the Clip at the current point
	 * @author Alex Crowley 40121793
	 */
	public void pauseSound()
	{
		audioClip.stop();
	}
	
	/**
	 * This method plays the Clip at the current point
	 * @author Alex Crowley 40121793
	 */
	public void resumeSound()
	{
		audioClip.start();
	}
	
	/**
	 * This method closes the sound
	 * @author Alex Crowley 40121793
	 */
	public void closeSound()
	{
		audioClip.close();
	}
	
	/**
	 * This method is only used for the game soundtrack and causes it to loop until stopped.
	 * @author Alex Crowley 40121793
	 */
	public void loopSound()
	{
		audioClip.loop(Clip.LOOP_CONTINUOUSLY);;
	}

}
