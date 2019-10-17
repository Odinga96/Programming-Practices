import java.io.Serializable;

public class Statistic implements Serializable {
	
	private static final long serialVersionUID = -200347581739009347L;
	private StatType type;
	private long value;
	
	public enum StatType {
		HEALTH,
		STAMINA,
		STRENGTH,
		SPIRIT,
		AGILITY,
		INTELLECT,
	}

	public Statistic() {
		this.type = type;
		this.value = value;
	}

	public StatType getType() {
		return type;
	}

	public void setType(StatType type) {
		this.type = type;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	};
	
	
	
}
