package basis.chapter01;

/**
 * Created by Administrator on 2015/4/11.
 * Runner
 */
public class Runner implements Runnable{

    int index = 0;

    public Runner(int index){
        this.index = index;
    }

    public void run(){
        long time = (long)(Math.random()*1000);
        //����̵߳����ֺ�ʹ��Ŀ��������ߵ�ʱ��
        for (int i = 0; i < 20; i++) {
            System.out.println("�߳�: "+ Thread.currentThread().getName()
                    + "(Ŀ�����" + index +"):Sleeping " + time + "ms");
        }
    }
}
