package ers;

public class Type {
	private int typeId;
	private String type;
	
	public Type() {
		super();
	}

	public Type(int typeId, String type) {
		super();
		this.typeId = typeId;
		this.type = type;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ReimbursementType [typeId=" + typeId + ", type=" + type + "]";
	}
}
