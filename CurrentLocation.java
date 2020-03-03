public class CurrentLocation {
	boolean existanceCheck;

	public boolean isExistanceCheck() {
		return existanceCheck;
	}

	public void setExistanceCheck(boolean existanceCheck) {
		this.existanceCheck = existanceCheck;
	}

	Block currentBlock;

	public Block getCurrentBlock() {
		return currentBlock;
	}

	public void setCurrentBlock(Block currentBlock) {
		this.currentBlock = currentBlock;
	}

	CurrentLocation(boolean value, Block block) {
		this.existanceCheck = value;
		this.currentBlock = block;
	}

}