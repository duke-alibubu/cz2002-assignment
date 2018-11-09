package entities;
import java.util.ArrayList;
import java.io.Serializable;

public class Grade extends Field implements Serializable
{
	Grade(Assessment Assess)
	{
		Distribution = Assess.emptyDistribution();
	}
	public boolean editComponent(String Name, float Mark)
	{
		return super.editComponent(Name, Mark);
	}
	
}
