package org.example.lcs.service.operation;

public interface Operation<REQ, RES> {
   default RES execute(REQ request){
       return null;
   };
}
