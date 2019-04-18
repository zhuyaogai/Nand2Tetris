package com.fantasy.cmd;

import com.fantasy.utils.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class C_COMMAND extends Command {

    private String cmd;
    private String dest = null;
    private String comp = null;
    private String jump = null;
    public C_COMMAND(String cmd) {
        this.cmd = StringUtil.getFirstPart(cmd);
        System.out.println(this.cmd);
        init();
    }

    private void init() {
        String[] split = cmd.split(";");
        if (split.length == 2)
            jump = split[1];

        String[] split1 = split[0].split("=");
        if (split1.length == 2) {
            dest = split1[0];
            comp = split1[1];
        } else {
            comp = split1[0];
        }
    }

    public String getDest() {
        return dest;
    }

    public String getComp() {
        return comp;
    }

    public String getJump() {
        return jump;
    }
}
