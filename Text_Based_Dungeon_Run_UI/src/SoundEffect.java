/*************************************************************************
	Copyright © 2021 Konstantinidis Konstantinos
	Code created with the help of: https://www.youtube.com/c/RyiSnow

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at 

	      http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software 
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and 
	limitations under the License.
*************************************************************************/
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {

	Clip clip;
	
	public void setURL(URL soundFileName) {
		
		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundFileName);
			clip = AudioSystem.getClip();
			clip.open(sound);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void play() {
		clip.setFramePosition(0);
		clip.start();
	}
}
