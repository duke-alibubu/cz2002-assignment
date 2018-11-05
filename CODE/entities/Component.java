package entities;
import java.util.ArrayList;

public class Component
{
	private String Name;
	private float Value;
	public Component(String Name, float Value)
	{
		this.Name = new String(Name);
		this.Value = Value;
	}
	public Component(String Name)
	{
		this.Name = new String(Name);
		this.Value = 0;
	}
	public String getName()
	{
		return new String(Name);
	}
	public float getValue()
	{
		return Value;
	}
	public void setValue(float Value)
	{
		this.Value = Value;
	}
}
