package Codice;

public class Player {
private String name;
private int points;
private boolean[] a;
private static final int[] M = {0,1,2,3,4,5,6,7,8,0,3,6,1,4,7,2,5,8,0,4,8,2,4,6};
private boolean x; //é x, non è o

public Player(String n)
{
	setPoints(0);
	setName(n);
	resetPlayer();
}


public void incrementPoints()
{
	points++;
}

public void playerPlayed(int n) //number that player played
{
	if(n<0 || n>8) return;
	a[n]=true;
}

public boolean getA(int i)
{
	if(i<0 || i>8) return false;
	return a[i];
}

		
public boolean win()
{
	int ok=0;
	int kont=0;
	int i=0;
	while(i<24){
		if(a[M[i]] ==true)
				{
					ok++;
				}
		if(ok==3)
			return true;
		kont++;
		if(kont==3)
		{
			ok=0;
			kont=0;
		}
		i++;
	}
	return false;
}
public void resetPlayer()
{
	a = new boolean[9];
	for(int i=0;i<9;i++) a[i]=false;
}

public void printA()
{
	for(int i=0;i<9;i++)
	{
		System.out.print(i+1 + ")" + a[i]);
	}
	System.out.println("");
}


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getPoints() {
	return points;
}
public void setPoints(int points) {
	this.points = points;
}


public boolean isX() {
	return x;
}

public int getFilled()
{
	int k=0;
	for(int i=0;i<9;i++)
	{
		if(a[i]==true)
			k++;
	}
	return k;
}

public void setA(int n)
{
	a[n]=true;
}

}
