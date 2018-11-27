
public class Arc {
	private Edge support;
	private boolean reversed;
	
	public Arc(Edge e, boolean reversed) {
		this.support = e;
		this.reversed = reversed;
	}

	public Edge getEdge(){
		return support;
	}
	
	public int getSource() {
		return (reversed ? support.getDest() : support.getSource());
	}
	
	public int getDest() {
		return (reversed ? support.getSource() : support.getDest());
	}
	
}
