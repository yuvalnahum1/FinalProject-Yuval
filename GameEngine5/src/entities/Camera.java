package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position = new Vector3f(170,15,220);
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camera(){}
	
	public void move(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.z-=0.4f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x+=0.4f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x-=0.4f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.z+=0.4f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			position.y+=0.4f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
			position.y-=0.4f;
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	

}
