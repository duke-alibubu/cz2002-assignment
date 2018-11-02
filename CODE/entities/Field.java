package entities;
import java.util.ArrayList;

public class Field
{
	protected ArrayList<Component> Distribution;
	private int find(String Name)
	{
		int index = -1;
		for (int i=0; i<Distribution.size(); i++)
			if (Name.equals(Distribution.get(i).getName()))
			{
				index = i;
				break;
			}
		return index;
	}
	public ArrayList<Component> emptyDistribution()
	{
		ArrayList<Component> Empty = new ArrayList<Component>();
		Component aComponent;
		for (int i=0; i<Distribution.size(); i++)
		{
			aComponent = new Component(Distribution.get(i).getName());
			Empty.add(aComponent);
		}
		return Empty;
	}
	protected boolean addComponent(String Name, float Value)
	{
		if (find(Name) != -1)
			return false;

		Component aComponent = new Component(Name, Value);
		Distribution.add(aComponent);
		return true;
	}
	protected boolean addComponent(String Name)
	{
		if (find(Name) != -1)
			return false;

		Component aComponent = new Component(Name);
		Distribution.add(aComponent);
		return true;	
	}
	protected boolean editComponent(String Name, float Weight)
	{
		int index = find(Name);
		if (index == -1)
			return false;

		Component aComponent = Distribution.get(index);
		aComponent.setValue(Weight);
		return true;
	}
	protected boolean removeComponent(String Name)
	{
		int index = find(Name);
		if (index == -1)
			return false;
		Distribution.remove(index);
		return true;
	}
}
class Component
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
