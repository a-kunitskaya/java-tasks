package com.kunitskaya;

import com.kunitskaya.service.errors.OutOfMemoryErrorProvider;
import javassist.CannotCompileException;

public class MainModule4 {

    public static void main(String[] args) throws CannotCompileException, ClassNotFoundException {
        //OutOfMemoryErrorProvider.getOutOfMemotyErrorHeapSpace();
        OutOfMemoryErrorProvider.getOutOfMemotyErrorHeapSize();
        OutOfMemoryErrorProvider.getOutOfMemotyErrorHeapSizeObjects();
        OutOfMemoryErrorProvider.getOutOfMemotyErrorMetaspace();
        OutOfMemoryErrorProvider.getOutOfMemotyErrorRecursion();
        OutOfMemoryErrorProvider.getOutOfMemotyErrorNoRecursion();
    }
}
