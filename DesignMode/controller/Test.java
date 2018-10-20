package design_mode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Test extends Controller {
	
	@FXML
	private GridPane target;
	@FXML
	private ImageView arrow;
	@FXML
	private ImageView bomb;
	@FXML
	private ImageView sword;
	@FXML
	private ImageView hover_potion;
	@FXML
	private ImageView invincbility_potion;
	@FXML
	private ImageView coward;
	@FXML
	private ImageView strategist;
	@FXML
	private ImageView hound;
	@FXML
	private ImageView hunter;
	@FXML
	private ImageView boulder;
	@FXML
	private ImageView pit;
	@FXML
	private ImageView exit;
	@FXML
	private ImageView floor_switch;
	@FXML
	private ImageView close_door;
	@FXML
	private ImageView open_door;
	@FXML
	private ImageView wall;
	@FXML
	private ImageView treasure;
	@FXML
	private ImageView key;

	public Test(Stage s) {
		super(s);
	}
	
	public void initialize() {
		
	}
	
	@FXML
	private void DragDetectArrow(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = arrow.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(arrow.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectBomb(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = bomb.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(bomb.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectSword(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = sword.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(sword.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectHover(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = hover_potion.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(hover_potion.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectInvincibility(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = invincbility_potion.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(invincbility_potion.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectStrategist(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = strategist.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(strategist.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectHunter(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = hunter.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(hunter.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectCoward(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = coward.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(coward.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectHound(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = hound.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(hound.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectBoulder(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = boulder.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(boulder.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectOpenDoor(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = open_door.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(open_door.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectCloseDoor(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = close_door.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(close_door.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectPit(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = pit.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(pit.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectTreasure(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = treasure.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(treasure.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectExit(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = exit.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(exit.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectKey(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = key.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(key.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectFloorSwitch(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = floor_switch.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(floor_switch.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	@FXML
	private void DragDetectWall(MouseEvent event) {
		System.out.println("DragDetected");
		Dragboard db = wall.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent cb = new ClipboardContent();
		cb.putImage(wall.getImage());
		
		db.setContent(cb);
		event.consume();
	}
	
	
	@FXML
	private void DragOver(DragEvent event) {
		System.out.println("DragOver"+event.getX()+" "+event.getY());
		if (event.getDragboard().hasImage()) {
			event.acceptTransferModes(TransferMode.ANY);
		}
	}
	
	@FXML
	private void DragEnter(DragEvent event) {
		System.out.println("DragEntered");
	}
	
	@FXML
	private void DragExit(DragEvent event) {
		System.out.println("DragExited");
	}
	
	@FXML 
	private void DragDrop(DragEvent event) {
		//if (target.getN)
		//Dragboard db = event.getDragboard();
		
		System.out.println("DragDrop");
		Image img = event.getDragboard().getImage();
		ImageView pic = new ImageView();
		pic.setImage(img);
		target.add(pic, (int)event.getX()/32, (int)event.getY()/32+1);
	}
	
	@FXML
	private void DragDone(DragEvent event) {
		System.out.println("DragDone");
	}
	
}




//@FXML 
//private void DragDrop(DragEvent event) throws FileNotFoundException {
//	System.out.println("DragDrop");
//	List<File> files = event.getDragboard().getFiles();
//	Image img = new Image(new FileInputStream(files.get(0)));
//	imageView.setImage(img);
//}
//@FXML
//private void DragOver(DragEvent event) {
//	System.out.println("DragOver");
//	if (event.getDragboard().hasFiles()) {
//		event.acceptTransferModes(TransferMode.ANY);
//	}
//}
