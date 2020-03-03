public class CurrentCheckedLocation {
	int[] values_can_used;

	public int[] getValues_can_used() {
		return values_can_used;
	}

	public void setValues_can_used(int[] values_can_used) {
		this.values_can_used = values_can_used;
	}

	int[] track_used_column_values;

	public int[] getTrack_used_column_values() {
		return track_used_column_values;
	}

	public void setTrack_used_column_values(int[] track_used_column_values) {
		this.track_used_column_values = track_used_column_values;
	}

	int[] track_used_row_values;

	public int[] getTrack_used_row_values() {
		return track_used_row_values;
	}

	public void setTrack_used_row_values(int[] track_used_row_values) {
		this.track_used_row_values = track_used_row_values;
	}

	boolean isCovered;

	public boolean isCovered() {
		return isCovered;
	}

	public void setCovered(boolean isCovered) {
		this.isCovered = isCovered;
	}

	public CurrentCheckedLocation() {

	}

}