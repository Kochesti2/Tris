package Codice;

public class State {
private Player p1;
private Player p2;
private static final int[] M = {0,1,2,3,4,5,6,7,8,0,3,6,1,4,7,2,5,8,0,4,8,2,4,6};

public State(String n1,String n2)
{
	p1 = new Player(n1);
	p2 = new Player(n2);

}



private boolean win(boolean[] a)
{
	int ok=0;
	int kont=0;
	int i=0;
	while(i<24){
		ok=0;
		kont=0;
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
	
}
