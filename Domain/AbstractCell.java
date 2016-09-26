package Domain;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class AbstractCell extends JButton{

	private static final long serialVersionUID = 1L;
	public Integer xIndex;
	public Integer yIndex;
	public Integer totalIndex;
	public Boolean isSelected;
	public Boolean isLocked;
	public ImageIcon defaultIcon;
	public ImageIcon defaultDisabledIcon;
	
	public void setEnabledCell(Boolean b) {
		if (isLocked==false){
			super.setEnabled(b);
		}
		
	}
	
	public void setDefaultIcon(ImageIcon i){
		defaultIcon=i;
		super.setIcon(i);
	}
	
	public void setDefaultIcon(){
		if (isLocked==false){
			super.setIcon(defaultIcon);
		}
	}
	
	public void setDefaultDisabledIcon(ImageIcon i){
		defaultDisabledIcon=i;
		super.setDisabledIcon(i);
	}
	
	public void setDefaultDisabledIcon(){
		if (isLocked==false){
			super.setDisabledIcon(defaultDisabledIcon);
		}
	}
	
	public void setIconCell(ImageIcon i){
		if (isLocked==false){
			super.setIcon(i);
		}
	}
	
	public Integer getxIndex() {
		return xIndex;
	}
	public void setxIndex(Integer xIndex) {
		this.xIndex = xIndex;
	}
	public Integer getyIndex() {
		return yIndex;
	}
	public void setyIndex(Integer yIndex) {
		this.yIndex = yIndex;
	}
	public Integer getTotalIndex() {
		return totalIndex;
	}
	public void setTotalIndex(Integer totalIndex) {
		this.totalIndex = totalIndex;
	}
	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	public void setVisibleLocked(Boolean b){
		if (isLocked==false){
			setVisible(b);
		}
	}
}
