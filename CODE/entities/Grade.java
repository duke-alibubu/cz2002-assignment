package entities;
import java.util.ArrayList;

public class Grade extends Field
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