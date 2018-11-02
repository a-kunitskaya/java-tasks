package com.kunitskaya;

import com.kunitskaya.service.errors.ErrorProvider;

public class MainModule4 {

    public static void main(String[] args) throws Exception {
       // ErrorProvider.getOutOfMemoryErrorHeapSpace();
      //  ErrorProvider.getOutOfMemoryErrorHeapSpaceObjects();
        ErrorProvider.getOutOfMemoryErrorMetaspace();
        //ErrorProvider.getStackOverflowErrorRecursion();
       // ErrorProvider.getStackOverFlowErrorNoRecursion();


    }
}
