package Interfaces;

import Commands.CommandList;

import java.io.Serializable;
import java.util.Collections;

/**
 * класс для генерирования ID
 */
public class IDGenerator implements Serializable{
    private static final long serialVersionUID = 2;
    public static Long createID() {
        Long before = 0L;
        for (Long id: CommandList.getIDs()){
            if (before < id){
                before = id;
            }
        }
        return before+1;
    }
}
