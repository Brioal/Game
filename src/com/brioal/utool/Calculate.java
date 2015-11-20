package com.brioal.utool;

import com.brioal.frames.BoradFrame;

import java.util.ArrayList;
import java.util.List;


public class Calculate {
    private int state_color;//1为黑棋，-1为白棋，0为旁观者
    private int[][] datas = new int[20][20];

    public Calculate(int state_color) {
        this.state_color = state_color;
        for (int j = 0; j < datas.length; j++) {
            for (int k = 0; k < datas[0].length; k++) {
                datas[j][k] = 0;
            }
        }
    }
    public boolean JudegeWin(Point point) {
        int x = point.getX();

        int y = point.getY();
        int i_1 = 1;
        int count_1 = 1;
        while ((x+i_1<20) && (datas[x][y]==datas[x+i_1][y])){
            i_1++;
            count_1++;
        }
        i_1=1;
        while ((x-i_1>0)&&(datas[x][y]== datas[x-i_1][y])){
            i_1++;
            count_1++;
        }
        if (count_1 >= 5) {
            return true ;
        }
        int i_2 = 1 ;
        int  count_2 = 1 ;
        while ((y - i_2 > 0) && (datas[x][y] == datas[x][y - i_2])) {
            i_2++;
            count_2++;
        }
        i_2 = 1 ;
        while ((y + i_2 < 20) && (datas[x][y] == datas[x][y + i_2])) {
            i_2++;
            count_2++;
        }
        if (count_2 >= 5) {
            return true;
        }
        int i_3 = 1 ;
        int count_3 = 1 ;
        while ((x - i_3 > 0) && (y - i_3 > 0) && (datas[x][y] == datas[x - i_3][y - i_3])) {
            i_3++;
            count_3++;
        }
        i_3 = 1 ;
        while ((x + i_3 < 20) && (y + i_3 < 20) && (datas[x][y] == datas[x + i_3][y + i_3])) {
            i_3++;
            count_3++;
        }
        if (count_3 >= 5) {
            return true;
        }
        int i_4 = 1 ;
        int count_4 = 1 ;
        while ((x + i_4 < 20) && (y - i_4 > 0) && (datas[x][y] == datas[x + i_4][y - i_4])) {
            i_4++;
            count_4++;
        }
        i_4 = 1 ;
        while ((x - i_4 > 0) && (y + i_4 < 20) && (datas[x][y] == datas[x - i_4][y + i_4])) {
            i_4++;
            count_4++;
        }
        if (count_4 >= 5) {
            return true;
        }

        return false;
    }
    public void addPoint(Point point) {
        int x = point.getX();
        int y = point.getY();

        if (point.getState() == BoradFrame.STATE_BLACK) {
            System.out.println("添加黑子");
            if (datas[x][y] == 0) {
                datas[x][y] = 1;
            }

        } else {
            System.out.println("添加白子");
            if (datas[x][y] == 0) {
                datas[x][y] = -1;
            }
        }

    }

    public Point getNext() {
        List<Point> points = new ArrayList<>();
//        if ((pingjia3(datas) == 0) && (pingjia4(datas) == 0) &&
//                (pingjia5(datas) == 0) && (pingjia6(datas) == 0) &&
//                (pingjia7(datas) == 0) && (pingjia8(datas) == 0) &&
//                (pingjia9(datas) == 0) && (pingjia10(datas) == 0) &&
//                (pingjia(datas) == 0)) {
        System.out.println(pingjia3(datas));
        System.out.println(pingjia4(datas));
        System.out.println(pingjia5(datas));
        System.out.println(pingjia6(datas));
        System.out.println(pingjia7(datas));
        System.out.println(pingjia8(datas));
        System.out.println(pingjia9(datas));
        System.out.println(pingjia10(datas));
        System.out.println(pingjia(datas));
//        pingjia5(datas);
//        pingjia6(datas);
//        pingjia7(datas);
//        pingjia8(datas);
//        pingjia9(datas);
//        pingjia10(datas);
//        pingjia(datas);
        int max = 0;

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                System.out.print(datas[i][j] + "\t");
                if (max <= datas[i][j]) {
                    max = datas[i][j];

                }
            }
            System.out.println();
        }
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (max == datas[i][j]) {
//                    System.out.println(i+"...."+j);
                    points.add(new Point(i, j));
                }
            }
        }
        int select = (int) (Math.random() * points.size());
        for (int i = 0; i < points.size(); i++) {
//            System.out.println(points.get(i).getX());
        }
        Point result = points.get(select);
        if (state_color == BoradFrame.STATE_BLACK) {
            result.setState(Point.STATE_BLACK);

        } else {
            result.setState(Point.STATE_WHITE);

        }
        addPoint(result);
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[0].length; j++) {
                if (datas[i][j] != 1 && datas[i][j] != -1) {
                    datas[i][j] = 0;
                }
            }
        }
//        System.out.println("随机选择到了" + result.getX() + ";" + result.getY());
        return result;
    }

//    else
//
//    {
//        return null;
//    }


//    }


    public int pingjia3(int[][] mind) {
        int countm = 0;
        int county = 0;
        int space = 0;

        for (int i = 15; i < 20; i++)
            for (int j = 1; j < 5; j++) {

                for (int k = 0; k < 5; k++) {
                    if (mind[i][j + k] != state_color && mind[i][j + k] != -state_color) space++;
                    if (mind[i][j + k] == state_color) countm++;
                    if (mind[i][j + k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                for (int k = 0; k < 5; k++) {
                    if (mind[i - k][j] != state_color && mind[i - k][j] != -state_color) space++;
                    if (mind[i - k][j] == state_color) countm++;
                    if (mind[i - k][j] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                for (int k = 0; k < 5; k++) {
                    if (mind[i - k][j + k] != state_color && mind[i - k][j + k] != -state_color) space++;
                    if (mind[i - k][j + k] == state_color) countm++;
                    if (mind[i - k][j + k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }


            }
        return 0;
    }

    public int pingjia4(int[][] mind) {
        int countm = 0;
        int county = 0;
        int space = 0;
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {

                for (int k = 0; k < 5; k++) {
                    if (mind[i + k][j] != state_color && mind[i + k][j] != -state_color) space++;
                    if (mind[i + k][j] == state_color) countm++;
                    if (mind[i + k][j] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                for (int k = 0; k < 5; k++) {
                    if (mind[i][j + k] != state_color && mind[i][j + k] != -state_color) space++;
                    if (mind[i][j + k] == state_color) countm++;
                    if (mind[i][j + k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                for (int k = 0; k < 5; k++) {
                    if (mind[i + k][j + k] != state_color && mind[i + k][j + k] != -state_color) space++;
                    if (mind[i + k][j + k] == state_color) countm++;
                    if (mind[i + k][j + k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }

            }
        }
        return 0;
    }

    public int pingjia5(int[][] mind) {
        int countm = 0;
        int county = 0;
        int space = 0;
        for (int i = 15; i < 20; i++) {
            for (int j = 15; j < 20; j++) {
                for (int k = 0; k < 5; k++) {
                    if (mind[i][j - k] != state_color && mind[i][j - k] != -state_color) space++;
                    if (mind[i][j - k] == state_color) countm++;
                    if (mind[i][j - k] == -state_color) county++;

                }
                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                for (int k = 0; k < 5; k++) {
                    if (mind[i - k][j] != state_color && mind[i - k][j] != -state_color) space++;
                    if (mind[i - k][j] == state_color) countm++;
                    if (mind[i - k][j] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                for (int k = 0; k < 5; k++) {
                    if (mind[i - k][j - k] != state_color && mind[i - k][j - k] != -state_color) space++;
                    if (mind[i - k][j - k] == state_color) countm++;
                    if (mind[i - k][j - k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
            }
        }
        return 0;
    }

    public int pingjia6(int[][] mind) {
        int countm = 0;
        int county = 0;
        int space = 0;
        for (int i = 1; i < 5; i++)
            for (int j = 15; j < 20; j++) {
                for (int k = 0; k < 5; k++) {
                    if (mind[i][j - k] != state_color && mind[i][j - k] != -state_color) space++;
                    if (mind[i][j - k] == state_color) countm++;
                    if (mind[i][j - k] == -state_color) county++;

                }
                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                for (int k = 0; k < 5; k++) {
                    if (mind[i + k][j] != state_color && mind[i + k][j] != -state_color) space++;
                    if (mind[i + k][j] == state_color) countm++;
                    if (mind[i + k][j] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                for (int k = 0; k < 5; k++) {
                    if (mind[i + k][j - k] != state_color && mind[i + k][j - k] != -state_color) space++;
                    if (mind[i + k][j - k] == state_color) countm++;
                    if (mind[i + k][j - k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
            }
        return 0;
    }

    public int pingjia7(int[][] mind) {
        int countm = 0;
        int county = 0;
        int space = 0;
        int jinshou=0;
        for (int i = 5; i < 15; i++)
            for (int j = 1; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (mind[i][j + k] != state_color && mind[i][j + k] != -state_color) space++;
                    if (mind[i][j + k] == state_color) countm++;
                    if (mind[i][j + k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                //上五个


                for (int k = 0; k < 5; k++) {
                    if (mind[i + k][j] != state_color && mind[i + k][j] != -state_color) space++;
                    if (mind[i + k][j] == state_color) countm++;
                    if (mind[i + k][j] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }

                //下五个

                for (int k = 0; k < 5; k++) {
                    if (mind[i - k][j] != state_color && mind[i - k][j] != -state_color) space++;
                    if (mind[i - k][j] == state_color) countm++;
                    if (mind[i - k][j] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                for (int k = 0; k < 5; k++) {
                    if (mind[i + k][j + k] != state_color && mind[i + k][j + k] != -state_color) space++;
                    if (mind[i + k][j + k] == state_color) countm++;
                    if (mind[i + k][j + k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                //右下
                for (int k = 0; k < 5; k++) {
                    if (mind[i - k][j + k] != state_color && mind[i - k][j + k] != -state_color) space++;
                    if (mind[i - k][j + k] == state_color) countm++;
                    if (mind[i - k][j + k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }if(jinshou>=2)mind[i][j]=0;
                jinshou=0;
            }
        return 0;
    }

    public int pingjia8(int[][] mind) {
        int countm = 0;
        int county = 0;
        int space = 0;
        int jinshou=0;
        for (int i = 15; i < 20; i++)
            for (int j = 5; j < 15; j++) {
                for (int k = 0; k < 5; k++) {
                    if (mind[i][j - k] != state_color && mind[i][j - k] != -state_color) space++;
                    if (mind[i][j - k] == state_color) countm++;
                    if (mind[i][j - k] == -state_color) county++;

                }
                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                //右边五个
                for (int k = 0; k < 5; k++) {
                    if (mind[i][j + k] != state_color && mind[i][j + k] != -state_color) space++;
                    if (mind[i][j + k] == state_color) countm++;
                    if (mind[i][j + k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                for (int k = 0; k < 5; k++) {
                    if (mind[i - k][j] != state_color && mind[i - k][j] != -state_color) space++;
                    if (mind[i - k][j] == state_color) countm++;
                    if (mind[i - k][j] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                for (int k = 0; k < 5; k++) {
                    if (mind[i - k][j + k] != state_color && mind[i - k][j + k] != -state_color) space++;
                    if (mind[i - k][j + k] == state_color) countm++;
                    if (mind[i - k][j + k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                for (int k = 0; k < 5; k++) {
                    if (mind[i - k][j - k] != state_color && mind[i - k][j - k] != -state_color) space++;
                    if (mind[i - k][j - k] == state_color) countm++;
                    if (mind[i - k][j - k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }if(jinshou>=2)mind[i][j]=0;
                jinshou=0;

            }
        return 0;
    }

    public int pingjia9(int[][] mind) {
        int countm = 0;
        int county = 0;
        int space = 0;
        int jinshou=0;
        for (int i = 1; i < 5; i++)
            for (int j = 5; j < 15; j++) {
                for (int k = 0; k < 5; k++) {
                    if (mind[i][j - k] != state_color && mind[i][j - k] != -state_color) space++;
                    if (mind[i][j - k] == state_color) countm++;
                    if (mind[i][j - k] == -state_color) county++;

                }
                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                //右边五个
                for (int k = 0; k < 5; k++) {
                    if (mind[i][j + k] != state_color && mind[i][j + k] != -state_color) space++;
                    if (mind[i][j + k] == state_color) countm++;
                    if (mind[i][j + k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                //上五个


                for (int k = 0; k < 5; k++) {
                    if (mind[i + k][j] != state_color && mind[i + k][j] != -state_color) space++;
                    if (mind[i + k][j] == state_color) countm++;
                    if (mind[i + k][j] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                for (int k = 0; k < 5; k++) {
                    if (mind[i + k][j + k] != state_color && mind[i + k][j + k] != -state_color) space++;
                    if (mind[i + k][j + k] == state_color) countm++;
                    if (mind[i + k][j + k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                for (int k = 0; k < 5; k++) {
                    if (mind[i + k][j - k] != state_color && mind[i + k][j - k] != -state_color) space++;
                    if (mind[i + k][j - k] == state_color) countm++;
                    if (mind[i + k][j - k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }if(jinshou>=2)mind[i][j]=0;
                jinshou=0;
            }
        return 0;
    }

    public int pingjia10(int[][] mind) {
        int countm = 0;
        int county = 0;
        int space = 0;
        int jinshou=0;
        for (int i = 5; i < 15; i++)
            for (int j = 15; j < 20; j++) {
                for (int k = 0; k < 5; k++) {
                    if (mind[i][j - k] != state_color && mind[i][j - k] != -state_color) space++;
                    if (mind[i][j - k] == state_color) countm++;
                    if (mind[i][j - k] == -state_color) county++;

                }
                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                for (int k = 0; k < 5; k++) {
                    if (mind[i + k][j] != state_color && mind[i + k][j] != -state_color) space++;
                    if (mind[i + k][j] == state_color) countm++;
                    if (mind[i + k][j] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }

                //下五个

                for (int k = 0; k < 5; k++) {
                    if (mind[i - k][j] != state_color && mind[i - k][j] != -state_color) space++;
                    if (mind[i - k][j] == state_color) countm++;
                    if (mind[i - k][j] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }


                //左斜上五个

                for (int k = 0; k < 5; k++) {
                    if (mind[i + k][j - k] != state_color && mind[i + k][j - k] != -state_color) space++;
                    if (mind[i + k][j - k] == state_color) countm++;
                    if (mind[i + k][j - k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }

                //左斜下五个

                for (int k = 0; k < 5; k++) {
                    if (mind[i - k][j - k] != state_color && mind[i - k][j - k] != -state_color) space++;
                    if (mind[i - k][j - k] == state_color) countm++;
                    if (mind[i - k][j - k] == -state_color) county++;

                }

                if (countm > 0 && county > 0) {
                    mind[i][j] += 0;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else if (space == 5) {
                    mind[i][j] += 7;
                    countm = 0;
                    county = 0;
                    space = 0;
                } else {if(countm>=2)jinshou+=1;
                    switch (countm) {
                        case 5:
                            return 1;
                        case 4:
                            mind[i][j] += 800000;
                            break;
                        case 3:
                            mind[i][j] += 15000;
                            break;
                        case 2:
                            mind[i][j] += 800;
                            break;
                        case 1:
                            mind[i][j] += 35;

                    }
                    //对方棋子

                    switch (county) {
                        case 5:
                            return -1;
                        case 4:
                            mind[i][j] += 100000;
                            break;
                        case 3:
                            mind[i][j] += 1800;
                            break;
                        case 2:
                            mind[i][j] += 400;
                            break;
                        case 1:
                            mind[i][j] += 15;

                    }
                    countm = 0;
                    county = 0;
                    space = 0;
                }
                if(jinshou>=2)mind[i][j]=0;
                jinshou=0;
            }
        return 0;
    }

    public int pingjia(int[][] mind) {
        int countm = 0;
        int county = 0;
        int space = 0;
        int jinshou=0;
        for (int i = 1; i < 20; i++)
            for (int j = 1; j < 20; j++) {


                if (i > 4 && i < 15 && j > 4 && j < 15) {
                    if (mind[i][j] != state_color && mind[i][j] != -state_color)
                    //左边五个
                    {
                        for (int k = 0; k < 5; k++) {
                            if (mind[i][j - k] != state_color && mind[i][j - k] != -state_color) space++;
                            if (mind[i][j - k] == state_color) countm++;
                            if (mind[i][j - k] == -state_color) county++;

                        }
                        if (countm > 0 && county > 0) {
                            mind[i][j] += 0;
                            countm = 0;
                            county = 0;
                            space = 0;
                        } else if (space == 5) {
                            mind[i][j] += 7;
                            countm = 0;
                            county = 0;
                            space = 0;
                        } else {if(countm>=2)jinshou+=1;
                            switch (countm) {
                                case 5:
                                    return 1;
                                case 4:
                                    mind[i][j] += 800000;
                                    break;
                                case 3:
                                    mind[i][j] += 15000;
                                    break;
                                case 2:
                                    mind[i][j] += 800;
                                    break;
                                case 1:
                                    mind[i][j] += 35;

                            }
                            //对方棋子

                            switch (county) {
                                case 5:
                                    return -1;
                                case 4:
                                    mind[i][j] += 100000;
                                    break;
                                case 3:
                                    mind[i][j] += 1800;
                                    break;
                                case 2:
                                    mind[i][j] += 400;
                                    break;
                                case 1:
                                    mind[i][j] += 15;

                            }
                            countm = 0;
                            county = 0;
                            space = 0;
                        }
                        //右边五个
                        for (int k = 0; k < 5; k++) {
                            if (mind[i][j + k] != state_color && mind[i][j + k] != -state_color) space++;
                            if (mind[i][j + k] == state_color) countm++;
                            if (mind[i][j + k] == -state_color) county++;

                        }

                        if (countm > 0 && county > 0) {
                            mind[i][j] += 0;
                            countm = 0;
                            county = 0;
                            space = 0;
                        } else if (space == 5) {
                            mind[i][j] += 7;
                            countm = 0;
                            county = 0;
                            space = 0;
                        } else {if(countm>=2)jinshou+=1;
                            switch (countm) {
                                case 5:
                                    return 1;
                                case 4:
                                    mind[i][j] += 800000;
                                    break;
                                case 3:
                                    mind[i][j] += 15000;
                                    break;
                                case 2:
                                    mind[i][j] += 800;
                                    break;
                                case 1:
                                    mind[i][j] += 35;

                            }
                            //对方棋子

                            switch (county) {
                                case 5:
                                    return -1;
                                case 4:
                                    mind[i][j] += 100000;
                                    break;
                                case 3:
                                    mind[i][j] += 1800;
                                    break;
                                case 2:
                                    mind[i][j] += 400;
                                    break;
                                case 1:
                                    mind[i][j] += 15;

                            }
                            countm = 0;
                            county = 0;
                            space = 0;
                        }
                        //上五个


                        for (int k = 0; k < 5; k++) {
                            if (mind[i + k][j] != state_color && mind[i + k][j] != -state_color) space++;
                            if (mind[i + k][j] == state_color) countm++;
                            if (mind[i + k][j] == -state_color) county++;

                        }

                        if (countm > 0 && county > 0) {
                            mind[i][j] += 0;
                            countm = 0;
                            county = 0;
                            space = 0;
                        } else if (space == 5) {
                            mind[i][j] += 7;
                            countm = 0;
                            county = 0;
                            space = 0;
                        } else {if(countm>=2)jinshou+=1;
                            switch (countm) {
                                case 5:
                                    return 1;
                                case 4:
                                    mind[i][j] += 800000;
                                    break;
                                case 3:
                                    mind[i][j] += 15000;
                                    break;
                                case 2:
                                    mind[i][j] += 800;
                                    break;
                                case 1:
                                    mind[i][j] += 35;

                            }
                            //对方棋子

                            switch (county) {
                                case 5:
                                    return -1;
                                case 4:
                                    mind[i][j] += 100000;
                                    break;
                                case 3:
                                    mind[i][j] += 1800;
                                    break;
                                case 2:
                                    mind[i][j] += 400;
                                    break;
                                case 1:
                                    mind[i][j] += 15;

                            }
                            countm = 0;
                            county = 0;
                            space = 0;
                        }

                        //下五个

                        for (int k = 0; k < 5; k++) {
                            if (mind[i - k][j] != state_color && mind[i - k][j] != -state_color) space++;
                            if (mind[i - k][j] == state_color) countm++;
                            if (mind[i - k][j] == -state_color) county++;

                        }

                        if (countm > 0 && county > 0) {
                            mind[i][j] += 0;
                            countm = 0;
                            county = 0;
                            space = 0;
                        } else if (space == 5) {
                            mind[i][j] += 7;
                            countm = 0;
                            county = 0;
                            space = 0;
                        } else {if(countm>=2)jinshou+=1;
                            switch (countm) {
                                case 5:
                                    return 1;
                                case 4:
                                    mind[i][j] += 800000;
                                    break;
                                case 3:
                                    mind[i][j] += 15000;
                                    break;
                                case 2:
                                    mind[i][j] += 800;
                                    break;
                                case 1:
                                    mind[i][j] += 35;

                            }
                            //对方棋子

                            switch (county) {
                                case 5:
                                    return -1;
                                case 4:
                                    mind[i][j] += 100000;
                                    break;
                                case 3:
                                    mind[i][j] += 1800;
                                    break;
                                case 2:
                                    mind[i][j] += 400;
                                    break;
                                case 1:
                                    mind[i][j] += 15;

                            }
                            countm = 0;
                            county = 0;
                            space = 0;
                        }


                        //左斜上五个

                        for (int k = 0; k < 5; k++) {
                            if (mind[i + k][j - k] != state_color && mind[i + k][j - k] != -state_color) space++;
                            if (mind[i + k][j - k] == state_color) countm++;
                            if (mind[i + k][j - k] == -state_color) county++;

                        }

                        if (countm > 0 && county > 0) {
                            mind[i][j] += 0;
                            countm = 0;
                            county = 0;
                            space = 0;
                        } else if (space == 5) {
                            mind[i][j] += 7;
                            countm = 0;
                            county = 0;
                            space = 0;
                        } else {if(countm>=2)jinshou+=1;
                            switch (countm) {
                                case 5:
                                    return 1;
                                case 4:
                                    mind[i][j] += 800000;
                                    break;
                                case 3:
                                    mind[i][j] += 15000;
                                    break;
                                case 2:
                                    mind[i][j] += 800;
                                    break;
                                case 1:
                                    mind[i][j] += 35;

                            }
                            //对方棋子

                            switch (county) {
                                case 5:
                                    return -1;
                                case 4:
                                    mind[i][j] += 100000;
                                    break;
                                case 3:
                                    mind[i][j] += 1800;
                                    break;
                                case 2:
                                    mind[i][j] += 400;
                                    break;
                                case 1:
                                    mind[i][j] += 15;

                            }
                            countm = 0;
                            county = 0;
                            space = 0;
                        }

                        //左斜下五个

                        for (int k = 0; k < 5; k++) {
                            if (mind[i - k][j - k] != state_color && mind[i - k][j - k] != -state_color) space++;
                            if (mind[i - k][j - k] == state_color) countm++;
                            if (mind[i - k][j - k] == -state_color) county++;

                        }

                        if (countm > 0 && county > 0) {
                            mind[i][j] += 0;
                            countm = 0;
                            county = 0;
                            space = 0;
                        } else if (space == 5) {
                            mind[i][j] += 7;
                            countm = 0;
                            county = 0;
                            space = 0;
                        } else {if(countm>=2)jinshou+=1;
                            switch (countm) {
                                case 5:
                                    return 1;
                                case 4:
                                    mind[i][j] += 800000;
                                    break;
                                case 3:
                                    mind[i][j] += 15000;
                                    break;
                                case 2:
                                    mind[i][j] += 800;
                                    break;
                                case 1:
                                    mind[i][j] += 35;

                            }
                            //对方棋子

                            switch (county) {
                                case 5:
                                    return -1;
                                case 4:
                                    mind[i][j] += 100000;
                                    break;
                                case 3:
                                    mind[i][j] += 1800;
                                    break;
                                case 2:
                                    mind[i][j] += 400;
                                    break;
                                case 1:
                                    mind[i][j] += 15;

                            }
                            countm = 0;
                            county = 0;
                            space = 0;
                        }
                        //右上五个

                        for (int k = 0; k < 5; k++) {
                            if (mind[i + k][j + k] != state_color && mind[i + k][j + k] != -state_color) space++;
                            if (mind[i + k][j + k] == state_color) countm++;
                            if (mind[i + k][j + k] == -state_color) county++;

                        }

                        if (countm > 0 && county > 0) {
                            mind[i][j] += 0;
                            countm = 0;
                            county = 0;
                            space = 0;
                        } else if (space == 5) {
                            mind[i][j] += 7;
                            countm = 0;
                            county = 0;
                            space = 0;
                        } else {if(countm>=2)jinshou+=1;
                            switch (countm) {
                                case 5:
                                    return 1;
                                case 4:
                                    mind[i][j] += 800000;
                                    break;
                                case 3:
                                    mind[i][j] += 15000;
                                    break;
                                case 2:
                                    mind[i][j] += 800;
                                    break;
                                case 1:
                                    mind[i][j] += 35;

                            }
                            //对方棋子

                            switch (county) {
                                case 5:
                                    return -1;
                                case 4:
                                    mind[i][j] += 100000;
                                    break;
                                case 3:
                                    mind[i][j] += 1800;
                                    break;
                                case 2:
                                    mind[i][j] += 400;
                                    break;
                                case 1:
                                    mind[i][j] += 15;

                            }
                            countm = 0;
                            county = 0;
                            space = 0;
                        }
                        //右下
                        for (int k = 0; k < 5; k++) {
                            if (mind[i - k][j + k] != state_color && mind[i - k][j + k] != -state_color) space++;
                            if (mind[i - k][j + k] == state_color) countm++;
                            if (mind[i - k][j + k] == -state_color) county++;

                        }

                        if (countm > 0 && county > 0) {
                            mind[i][j] += 0;
                            countm = 0;
                            county = 0;
                            space = 0;
                        } else if (space == 5) {
                            mind[i][j] += 7;
                            countm = 0;
                            county = 0;
                            space = 0;
                        } else {if(countm>=2)jinshou+=1;
                            switch (countm) {
                                case 5:
                                    return 1;
                                case 4:
                                    mind[i][j] += 800000;
                                    break;
                                case 3:
                                    mind[i][j] += 15000;
                                    break;
                                case 2:
                                    mind[i][j] += 800;
                                    break;
                                case 1:
                                    mind[i][j] += 35;

                            }
                            //对方棋子

                            switch (county) {
                                case 5:
                                    return -1;
                                case 4:
                                    mind[i][j] += 100000;
                                    break;
                                case 3:
                                    mind[i][j] += 1800;
                                    break;
                                case 2:
                                    mind[i][j] += 400;
                                    break;
                                case 1:
                                    mind[i][j] += 15;

                            }
                            countm = 0;
                            county = 0;
                            space = 0;
                        }
                    }
                    if(jinshou>=2)mind[i][j]=0;
                    jinshou=0;
                }
            }
        return 0;

    }

}
