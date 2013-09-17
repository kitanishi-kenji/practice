
public class OpenClose {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=1; i<=100 ; i++){
			openClose(i);	
		}
	}
	
	private static void openClose(int size){
		byte[] array = new byte[size];
		for(int i=0 ; i<size ; i++){
			if(i==0){
				for(int j=0 ; j<size ;j++){
					array[j] = 0x0;
				}
			} else {
				for(int j=i ; j<size ;j+=(i+1)){
					array[j] ^=0x1;
				}
			}
		}
		System.out.print(size + ",");
		int openCount = 0;
		for(int i=0 ; i<size ;i++){
			System.out.print(array[i]);		
			if(array[i] == 0x0){
				openCount++;
			}
		}
		System.out.println("," + openCount);		
	}

}
