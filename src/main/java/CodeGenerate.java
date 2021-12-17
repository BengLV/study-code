import java.util.HashSet;
import java.util.Random;
import java.util.Set;


/**
 * @author lupeng
 */
public class CodeGenerate {

    private static final String[] baseAlphabet = new String[]{"A", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private static final String[] baseNum = new String[]{"2", "3", "4", "5", "6", "7", "9"};

    /**
     * 券码生成
     * 规则:
     * 组成：AAAA 1111 AAAA 1111
     * 特殊要求：
     * 1、不使用I和1，B和8，0和O
     * 2、只使用大写字母
     * 3、不可以连号生成兑换码
     */
    public static String codeGenerate() {
        int codeLength = 16;
        StringBuffer code = new StringBuffer();
        Random rand = new Random();
        for (int i = 0; i < codeLength; ++i) {
            //判断字母位置
            boolean alphabetLocation = (i >= 0 && i < 4) || (i >= 8 && i < 12);
            if (alphabetLocation) {
                int indexCode = rand.nextInt(baseAlphabet.length);
                code.append(baseAlphabet[indexCode]);
            } else {
                int indexCode = rand.nextInt(baseNum.length);
                code.append(baseNum[indexCode]);
            }
        }
        return code.toString();
    }

    public static Set<String> batchCodeGenerate(int amount) {
        Set<String> codeSet = new HashSet<>(amount);
        for (int i = 0; i < amount; ++i) {
            codeSet.add(codeGenerate());
        }
        return codeSet;
    }

    public static void main(String[] args) {
        /*Set<String> codeSet = batchCodeGenerate(100000);
        System.out.println(codeSet);
        System.out.println(codeSet.size());*/
        String a = new String("11").intern();
        a.intern();
        String b = "11";
        System.out.println(a == b);

        String c = new String("11") + new String("22");
        c.intern();
        String d = "1122";
        System.out.println(c == d);
    }
}
