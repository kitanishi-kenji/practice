
public class ChangedBits {

	/**
	 * 文字列を0と1のビットに変換する、0以外は1に変換
	 * @param s 文字列
	 * @return byte[] Bitを格納する配列
	 */
	public static byte[] toBits(String s){
		byte[] bytes = null;
		if(s != null && s.length() > 0){
			int length = s.length();
			// 全文字列が格納できるバイト数を確保
			int arrayLength = length / 8;
			if((length % 8) > 1){
				arrayLength++;
			}
			bytes = new byte[arrayLength];
			for(int i = 0 ; i<length ; i++){
				int index = length - (i+1);
				String c = s.substring(index, index+1);
				int arrayIndex = i / 8;
				int bitsIndex = i % 8;
				if(!c.equals("0")){
					bytes[arrayIndex] += (0x1 << bitsIndex); 
				}
			}
		}
		return bytes;
	}
	
	/**
	 * ビットを文字列に変換
	 * @param bytes
	 */
	public static String toString(byte[] bytes){
		int length = bytes.length;
		StringBuilder sb = new StringBuilder();
		for(int i=length-1 ; i>=0 ; i--){
			for (int j=7 ; j>=0 ; j--){
				int bit = 0x1 << j;
				if((bytes[i] & bit) > 0){
					sb.append("1");
				} else {
					sb.append("0");
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 偶数と奇数の値を切り替える
	 * @param bytes
	 * @return
	 */
	public static byte[] change(byte[] src){
		// 奇数と偶数の位置のみを残すビット
		int size = src.length;
		byte[] desc = new byte[size];
		for(int i=0 ; i<size ; i++){
			// 偶数(1,3,5...)を左にシフトして、奇数(0,2,4...)にし、偶数部分のビットを0にする
			byte odd = (byte)(((src[i]) >> 1) & 0x55);
			// 奇数(0,2,4...)を右にシフトして、偶数(1,3,5...)にし、奇数部分のビットを0にする
			byte even = (byte)(((src[i]) << 1) & 0xAA);
			// そして合体！
			desc[i] = (byte)(odd | even);
		}
		return desc;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		run("0101010101010101");
		run("1001100110011001");
		run("0110011001100110");
		run("1010101010101010");
		run("1100001111000011");
	}
	public static void run(String s){
		byte[] bytes = toBits(s);
		System.out.println("------------------");
		System.out.println("s:" + toString(bytes));
		System.out.println("d:" + toString(change(bytes)));
		
	}

}
