package view;

public interface RubricView {

	
	
	void setRubricClickListener(RubricClickListener rubricClickListener);
	
	
	 public interface RubricClickListener{
		 
		 void onClick(int x,int y);
			 
		 
		 
	 }
	
	
	
}


