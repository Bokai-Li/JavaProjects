package a7;

public class RegionImpl implements Region{
	private Coordinate tl;
	private Coordinate br;

	public RegionImpl(Coordinate a, Coordinate b)
	{
		if(a==null || b==null)
		{
			throw new IllegalArgumentException("Coodinate cannot be null");
		}
		int smallx = (a.getX()<=b.getX()) ? a.getX() : b.getX();
		int bigx = (a.getX()>=b.getX()) ? a.getX() : b.getX();
		int smally =(a.getY()<=b.getY()) ? a.getY() : b.getY();
		int bigy = (a.getY()>=b.getY()) ? a.getY() : b.getY();
		tl = new Coordinate(smallx,smally);
		br = new Coordinate(bigx,bigy);
		
	}
	
	@Override
	public Coordinate getUpperLeft() {
		return tl;
	}

	@Override
	public Coordinate getLowerRight() {
		return br;
	}

	@Override
	public int getTop() {
		return tl.getY();
	}

	@Override
	public int getBottom() {
		return br.getY();
	}

	@Override
	public int getLeft() {
		return tl.getX();
	}

	@Override
	public int getRight() {
		return br.getX();
	}

	@Override
	public Region intersect(Region other) throws NoIntersectionException {
		if  (other ==null)
			throw new NoIntersectionException();
		if(this.getLeft() > other.getRight() || this.getRight()<other.getLeft() || this.getTop()> other.getBottom() ||this.getBottom()<other.getTop())
			throw new NoIntersectionException();
		int top = this.getTop() > other.getTop() ? this.getTop() : other.getTop();
		int left = this.getLeft() > other.getLeft() ? this.getLeft() : other.getLeft();
		int bottom = this.getBottom() < other.getBottom() ? this.getBottom() : other.getBottom();
		int right = this.getRight() < other.getRight() ? this.getRight() : other.getRight();
		Region r = new RegionImpl(new Coordinate(left,top), new Coordinate(right,bottom));
		return r;
	}

	@Override
	public Region union(Region other) {
		if(other == null)
			return this;
		int top = this.getTop() < other.getTop() ? this.getTop() : other.getTop();
		int left = this.getLeft() < other.getLeft() ? this.getLeft() : other.getLeft();
		int bottom = this.getBottom() > other.getBottom() ? this.getBottom() : other.getBottom();
		int right = this.getRight() > other.getRight() ? this.getRight() : other.getRight();
		Region r = new RegionImpl(new Coordinate(left,top), new Coordinate(right,bottom));
		return r;
	}
	
	

}
