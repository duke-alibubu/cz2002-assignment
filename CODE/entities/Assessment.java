package entities;
import java.util.ArrayList;

public class Assessment extends Field
{
	public Assessment()
	{
		Distribution = new ArrayList<Component>();
	}
	public boolean addComponent(String Name, float Weight)
	{
		return super.addComponent(Name, Weight);
	}
	public boolean editComponent(String Name, float Weight)
	{
		return super.editComponent(Name, Weight);
	}
	public boolean removeComponent(String Name)
	{
		return super.removeComponent(Name);
	}
}