package structure.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 哈夫曼编码
 * 针对一个字符串  每种字符都是一个叶子节点  相同字符的出现次数即为该叶子节点的权值
 * 构建哈夫曼树 每一个字符的编码即为树的根节点到叶子节点的路径左为0 右为1
 * 哈夫曼编码的特性
 * 1、是变长编码，权值越大的节点编码长度越短 所以哈夫曼编码的压缩率是非常高的
 * 2、是前缀编码，即各个编码不互为前缀。在解码过程中能够保证一个码不会出现匹配到多个字符。这个特性是二叉树的叶子节点决定的
 *
 * 如果要根据哈夫曼编码压缩 主要针对的是 重复率较高的东西（字符串，文件，图片  任意东西）
 */
@Data
public class HuffmanCode {

    private HuffmanTree huffmanTree;

    private Map<String, String> encodeMap = new HashMap<>();
    private Map<String, String> decodeMap = new HashMap<>();

    public String encode(String data) {
        Map<Character, HuffmanNode> huffmanNodeMap = new HashMap<>();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            HuffmanNode huffmanNode = huffmanNodeMap.get(c);
            if (huffmanNode == null) {
                HuffmanNode tempNode = new HuffmanNode(String.valueOf(c));
                tempNode.setWeight(1);
                huffmanNodeMap.put(c, tempNode);
            } else {
                huffmanNode.setWeight(huffmanNode.getWeight() + 1);
            }
        }
        List<HuffmanNode> values = new ArrayList<>(huffmanNodeMap.values());
        huffmanTree = new HuffmanTree(values);
        StringBuilder encodeString = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            String huffmanCode = getHuffmanCode(huffmanTree.getRoot(), String.valueOf(c), "");
            encodeMap.put(String.valueOf(c), huffmanCode);
            decodeMap.put(huffmanCode, String.valueOf(c));
            encodeString.append(huffmanCode);
        }
        return encodeString.toString();
    }

    public String decode(String encodeString) {
        String originalString = "";
        String tempCode = "";
        for (int i = 0; i < encodeString.length(); i++) {
            tempCode += encodeString.charAt(i);
            if (decodeMap.get(tempCode) != null) {
                originalString += decodeMap.get(tempCode);
                tempCode = "";
                continue;
            }
        }
        return originalString;
    }

    /**
     * 根据value值去找路径 此处使用前序遍历
     */
    public String getHuffmanCode(HuffmanNode node, String value, String path) {
        if (node == null) {
            return null;
        }

        if (node.getValue() == null) {
            String leftHuffmanCode = getHuffmanCode(node.getLeft(), value, path);
            if (leftHuffmanCode != null) {
                //递归特性 需要把0写在前面 否则就倒了
                return "0" + leftHuffmanCode;
            }
            String rightHuffmanCode = getHuffmanCode(node.getRight(), value, path);
            if (rightHuffmanCode != null) {
                return "1" + rightHuffmanCode;
            }
        } else {
            if (node.getValue().equals(value)) {
                return path;
            }
        }
        return null;
    }

    /**
     * 根据哈夫曼编码压缩成byte数组 每8位变成一个byte
     */
    public byte[] zip(String huffmanCode) {
        int length = (huffmanCode.length() + 7) / 8;
        byte[] zip = new byte[length];
        int index = 0;
        for (int i = 0; i < huffmanCode.length(); i += 8) {
            String substring;
            if (i + 8 > huffmanCode.length()) {
                substring = huffmanCode.substring(i);
            } else {
                substring = huffmanCode.substring(i, i + 8);
            }

            zip[index] = (byte) Integer.parseInt(substring, 2);
            index++;
        }
        return zip;
    }

    public String unzip(byte[] zipArray) {
        String huffmanCode = "";
        for (int i = 0; i < zipArray.length; i++) {
            //Integer.toBinaryString()返回的是补码  所以如果是负数则会填满int的所有位数变成前面会有24个1
            //所以如果是负数只需要截取后八位
            //这里有一个问题 最后一位并不能确定是否需要补高位 todo
            boolean flag = true;
            huffmanCode += byte2String(zipArray[i], flag);
        }
        return huffmanCode;
    }

    /**
     * 反码：正数的反码与原码相同，负数的反码在原码的基础上，符号位不变，其余的按位取反
     * 补码：正数的补码就是原码本身，负数的补码是它的反码+1
     *
     * @param b    要转换的字节
     * @param flag 需要前面补0
     */
    public String byte2String(byte b, boolean flag) {
        int temp = b;
        String string;
        //有些正数需要前面补0
        if (flag) {
            temp |= 256; //按位或 0|1 = 1 256=>100000000
        }
        String tempString = Integer.toBinaryString(temp);
        if (flag || b < 0) {
            //跟256或过之后或者是负数 二进制位数必然大于8位 需要截取
            string = Integer.toBinaryString(temp).substring(tempString.length() - 8);
        } else {
            string = Integer.toBinaryString(temp);
        }

        return string;
    }

    public static void main(String[] args) {
        String data = "abbcccdddeeeefffffgggggg";
        System.out.println(data.getBytes().length);
        HuffmanCode huffmanCode = new HuffmanCode();
        String encodeString = huffmanCode.encode(data);
        System.out.println(encodeString);
        String decode = huffmanCode.decode(encodeString);
        System.out.println(decode);
        System.out.println(Arrays.toString(huffmanCode.zip(encodeString)));
        System.out.println(huffmanCode.unzip(huffmanCode.zip(encodeString)));
        System.out.println(huffmanCode.decode(huffmanCode.unzip(huffmanCode.zip(encodeString))));
    }
}
