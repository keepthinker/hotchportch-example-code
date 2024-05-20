package com.keepthinker.example.general.security.hutool;

import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.SmUtil;
import com.keepthinker.example.general.security.EncodeType;
import com.keepthinker.example.general.util.Utils;

public class Sm4Main {

    public static void main(String[] args) throws Exception {
        cn.hutool.crypto.symmetric.SM4 sm4 = SmUtil.sm4(KeyUtil.generateKey("SM4").getEncoded());
        byte[] entryptedStr = sm4.encrypt("01234567890123");
        System.out.println(Utils.encode(EncodeType.HEX, entryptedStr));



    }
}
