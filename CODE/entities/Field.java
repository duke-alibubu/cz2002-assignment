package entities;
import java.util.ArrayList;
import java.io.Serializable;

public class Field implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected ArrayList<Component> Distribution;
	private int find(String Name)
	{
		for (int i = 0; i<Distribution.size();i++) {
			if(Distribution.get(i).getName().equals(Name)) 
				return i;
		}
		return -1;
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
		if (index == -1) {
			return false;}

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
	public ArrayList<Component> getDistribution(){
		/*ArrayList<Component> CopyDistribution = new ArrayList<Component>();
		for (Component comp : Distribution) {
			CopyDistribution.add(new Component(comp));
		}
		return CopyDistribution;*/
		return Distribution;
	}
}
