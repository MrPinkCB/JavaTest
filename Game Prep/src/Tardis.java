import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Tardis extends Sprite implements Runnable {
	
	private Boolean visible, moving;
	private Thread t;
	private JLabel TardisLabel, DoctorLabel;
	private Doctor myDoctor;
	private JButton animationButton;
	

	public Boolean getVisible() {return visible;}	
	public Boolean getMoving() {return moving;}
	
	public void setVisible(Boolean visible) {this.visible = visible;}
	public void setMoving(Boolean moving) {this.moving = moving;}
	
	public void setDoctor(Doctor temp) {this.myDoctor = temp;}
	public void setAnimationButton(JButton temp) {this.animationButton = temp;}
	public void setTardisLabel(JLabel temp) {this.TardisLabel = temp;}
	public void setDoctorLabel(JLabel temp) {this.DoctorLabel = temp;}
	
	public Tardis() {
		super(124, 200, "tardis.png");
		this.visible = true;
		this.moving = false;
	}
	
	public Tardis(JLabel temp) {
		super(124, 200, "tardis.png");
		this.visible = true;
		this.moving = false;
		this.TardisLabel = temp;
	}
	
	
	public void hide( ) {this.visible = false; }
	public void show( ) {this.visible = true; }
	
	public void Display( ) {
		System.out.println("X. Y / vis: " + this.x + "," + this.y + " / " + this.visible);
	}
	


	public void moveTardis( ) {
		t = new Thread(this, "Tardis Thread");
		t.start();
	}
	@Override
	public void run() {
	this.moving = true;
	
	TardisLabel.setIcon(new ImageIcon( getClass().getResource("Tardis.png") ) );
	DoctorLabel.setIcon(new ImageIcon( getClass().getResource("Dw12.png") ) );
	
	while (moving) {
		//movement routine
		
		//get current X/Y
		int tx = this.x;
		int ty = this.y;
		
		
		
		tx += GameProperties.CHARACTER_STEP;
		
		if ( tx > GameProperties.SCREEN_WIDTH) {
			tx = -1 * this.width;
		}
		
		//this.x = tx;
		//this.y = ty;
		this.setX(tx);
		this.setY(ty);
		
		TardisLabel.setLocation(this.x, this.y);
		this.detectDoctorCollision();
		
		try {
			Thread.sleep(200);			
		} catch (Exception e) {}
		
		}
	}
	
	
	private void detectDoctorCollision() {
		if (this.r.intersects(myDoctor.getRectangle())) {
			System.out.println("BOOM!");
			this.moving = false;
			animationButton.setText("RUN");
			TardisLabel.setIcon(new ImageIcon( getClass().getResource("redTardis.png") ) );
			DoctorLabel.setIcon(new ImageIcon( getClass().getResource("redDw12.png") ) );
		}
	}
	
}
