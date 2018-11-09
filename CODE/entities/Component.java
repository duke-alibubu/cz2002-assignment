package entities;
import java.util.ArrayList;
import java.io.Serializable;

public class Component implements Serializable
{
	private String Name;
	private float Value;
	public Component(String Name, float Value)
	{
		this.Name = new String(Name);
		this.Value = Value;
	}
	public Component(Component c) {
		this.Name = new String(c.Name);
		this.Value = c.Value;
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
