/**
 * 九种常用排序算法工具类：
 * 插入排序：直接插入o(N^2)，二分插入O(N^2)，希尔排序：依赖增量d
 * 选择排序：简单选择o(N^2)，堆排序O(NlogN)
 * 交换排序：冒泡排序o(N^2)，快速排序(随机化可到达O(NlogN))
 * 归并排序O(NlogN)
 * 基数排序O(d(n+r)),d为位数，r为基数
 * 
 * 排序算法的选择
 *    1.数据规模较小
 *    （1）待排序列基本序的情况下，可以选择直接插入排序；
 *    （2）对稳定性不作要求宜用简单选择排序，对稳定性有要求宜用插入或冒泡
 *    2.数据规模不是很大
 *    （1）完全可以用内存空间，序列杂乱无序，对稳定性没有要求，快速排序，此时要付出log（N）的额外空间。    
 *    （2）序列本身可能有序，对稳定性有要求，空间允许下，宜用归并排序    
 *    3.数据规模很大    
 *    （1）对稳定性有求，则可考虑归并排序。    
 *    （2）对稳定性没要求，宜用堆排序    
 *    4.序列初始基本有序（正序）    
 *       宜用直接插入，冒泡
 */


public class SortUtil {
        
    /***********************************插入排序：直接插入，二分插入，希尔排序**********************/

        
    /**
        * 插入排序改进版，找到位置最后才插入
        * @param a
        * @return
        */

        

    public static int[] insertSort(int[] a){
            
    //假设第一个记录为已经待排序好的记录，那么要比较a.length-1个记录，所以外层循环是a.length-1次 

            
    //这里可以直接i=1从第二位开始处理也一样

            
        for(int i=1;i<a.length;i++) {
                
            //缓存待排序的记录

                
            int temp = a[i];
                
            int j;
            //这个是待插入的位置-1

                
            //跟前面已排序的记录做对比，找到合适的位置，建议从后往前面比，若是比最后一位小，则向前移动一位，否则就直接找到该位置

                
            for(j=i-1;j>=0;j--) {
                    
                //如果当前待排序的数比这一个排序的数大，则跳出循环，否则交换位置

                    
                if(temp>a[j]) {
                        
                    break;
                }else {
                        
                    //把当前记录往后面移动一位

                    a[j+1]=a[j];
                }
            }
            a[j+1]=temp;
        }
                
            return a;
    }
        
    /**
        * 二分插入排序
        * @param a
        * @return
        */

        

    public static int[] divideInsertSort(int[] a){
            
        //这里可以直接i=1从第二位开始处理也一样

            
        for(int i=1;i<a.length;i++) {
                
            //缓存待排序的记录

            int temp = a[i];
                
            //下面用二分法来在前面排好序的位置中找到插入的位置

                
            //找到前面已排序的起点

                
            int left = 0;
                
            //找到已排序的终点

                
            int right = i-1;
                
            //中间位置坐标

                
            int mid = 0;
                
            //如果左边小于右边，就表明数据大于1，可以用二分法

                
            while(left<=right) {
                    
                //这个如果是偶数，比如4，则第二位当做中间值，若是5则第三位

                mid = (right+left)/2;
                    
                //对比当前排序的数目和该数据的大小、

                    
                if(temp>a[mid]) {
                        
                    //继续右边比较

                    left = mid+1;
                }else {
                        
                    //继续左边比较

                    right = mid-1;
                }
            }
                
            //循环结束后，left值和right值一定相同，并且比最一次比较的mid向左或者右偏移一位，所以位置就是left插入

                
            //此时必须把已排序好的从left开始往后移动

                
            //这里找到位置后，需要把该位置往后面的都右移

                
            for(int j = i-1; j >= left; j--) {
                a[j+1] = a[j];
            }
                
            if(left != i){
                a[left] = temp;
            }
        }
            
        return a;
    }
        
    /**
        * 希尔排序
        * @param a
        * @return
        */

        

    public static int[] shellSort(int[] a){
            
            //主要是取增量d

            
        int d = a.length;
            
        while(true) {
            d=d/2;
                
            //开始进行分组插入排序,对d组进行排序，到最后d=1 就是一组排序

                
            for(int x=0;x<d;x++) {
                    
                //每一组分别进行直接插入排序

                    
                for(int i=x+d;i<a.length;i=i+d) {
                        
                    //缓存待排序的记录
                    
                    int temp = a[i];
                        
                    int j;
                    //这个是待插入的位置-1

                        
                    //跟前面已排序的记录做对比，找到合适的位置，建议从后往前面比，若是比最后一位小，则向前移动一位，否则就直接找到该位置

                        
                    for(j=i-d;j>=0;j=j-d) {
                            
                        //如果当前待排序的数比这一个排序的数大，则跳出循环，否则交换位置

                            
                        if(temp>a[j]) {
                                
                            break;
                        }else {
                                
                            //把当前记录往后面移动一位

                            a[j+d]=a[j];
                        }
                    }
                    a[j+d]=temp;
                }
            }
                
            //数组长度可能为1，那么这个值可能为0

                
            if(d == 1||d==0){
                System.out.println(d);
                    
                break;
            }
        }
            
        return a;
    }
        
    /***************************选择排序：简单选择，堆排序******************************/

        
    /**
        * 简单选择排序
        * @param a
        * @return
        */

        

    public static int[] choiceSort(int[] a){
            
        //这里每一个数都要做比较

            
        for (int i = 0; i < a.length; i++) {
                
            //假设第一个数是最小的数

                
            int min =a[i];
                
            int n=i; 
            //最小数的索引

                
            //从后面找出最小的数，以及最小的数的位置

                
            for(int j=i+1;j<a.length;j++) {
                    
                if(a[j]<min) {
                        
                    //最小数的值

                    min = a[j];
                        
                    //最小数的位置

                    n=j;
                }
            }
                
            //把当前的值和最小数的位置那个值替换

            a[n]=a[i];
            a[i]=min;
        }
            
        return a;
    }
        
    /**
        * 堆排序
        * @param a
        * @return
        */

        

    public static int[] heapSort(int[] a) {
            
        int arrayLength=a.length;  
            
        //循环建堆  

            
        for(int i=0;i<arrayLength-1;i++){  
                
            //建堆  

            buildMaxHeap(a,arrayLength-1-i);  
                
            //交换堆顶和最后一个元素  

            swap(a,0,arrayLength-1-i);  
        }  
            
        return a;
    }
        
    //对data数组从0到lastIndex建大顶堆

        

    private static void buildMaxHeap(int[] data, int lastIndex) {
            
        //从lastIndex处节点（最后一个节点）的父节点开始 

            
        for(int i=(lastIndex-1)/2;i>=0;i--){
                
            //k保存正在判断的节点 

                
            int k=i;
                
            //如果当前k节点的子节点存在  

                
            while(k*2+1<=lastIndex){
                    
                //k节点的左子节点的索引 

                    
                int biggerIndex=2*k+1;
                    
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在

                    
                if(biggerIndex<lastIndex){  
                        
                    //若果右子节点的值较大  

                        
                    if(data[biggerIndex]<data[biggerIndex+1]){  
                            
                        //biggerIndex总是记录较大子节点的索引  
                        biggerIndex++;  
                    }  
                }  
                    
                //如果k节点的值小于其较大的子节点的值  

                    
                if(data[k]<data[biggerIndex]){  
                        
                    //交换他们  

                    swap(data,k,biggerIndex);  
                        
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值  

                    k=biggerIndex;  
                }else{  
                        
                    break;  
                }  
            }
        }
    }
        
    //交换

    private static void swap (int[] data, int i, int j) {  
            
        int tmp=data[i];  
        data[i]=data[j];  
        data[j]=tmp;  
    } 
        
    /*****************************交换排序：冒泡排序，快速排序************************/

        
    /**
        * 冒泡排序
        * @param a
        * @return
        */

        

    public static int[] bubbleSort(int[] a) {
            
        //1、两两比较，如果前者比后者者大则交换位置

            
        //2、每遍历一圈最大的数就会冒到最后，则确定了本轮比较中的最大值放到最后不动

            
        //3、循环1、2直至遍历完所有

            
        for (int i = 0; i < a.length-1; i++) {
            //外循环循环n-1次

                
            for (int j = 1; j < a.length-i; j++) {
                //内循环每一次要比较n-i次              
                if(a[j-1]>a[j]){
                        
                    int temp=a[j-1];
                    a[j-1]=a[j];
                    a[j]=temp;
                }
            }
        }
            
    return a;
    }
        
    /**
        * 快速排序
        * @param a
        * @return
        */

        

    public static int[] quickSort(int[] a) {       
        if(a.length>0) {
            quickSort(a,0,a.length-1);
        }
            
        return a;
    }
        

    private static void quickSort(int[] a, int low, int high) {
            
        if(low<high) {
                
            //选择基准元素

                
            int middle = getMiddle(a,low,high);
            quickSort(a, 0, middle-1);
            quickSort(a, middle+1, high);
        }
    }
        
    private static int getMiddle(int[] a, int low, int high) {
            
        //假设第一个是基准元素

        int temp = a[low];        
        while(low<high) {
                
        //找到比基准元素小的位置

                
            while(low<high&&a[high]>=temp) {
                high--;
            }
            a[low] = a[high];
                
            //当队首元素小于等于tmp时,向前挪动low指针

                
            while(low<high && a[low]<=temp){
                low++;
            }
            a[high] = a[low];
        }
        a[low] = temp;
            
        return low;
    }
        
    /**************************归并排序***********************/

        
    /**
        * 归并排序
        * @param a
        * @param left
        * @param right
        */

        

    public static int[] mergeSort(int[] a) {
            
        return mergeSort(a, 0, a.length-1);
    }
        

    private static int[] mergeSort(int[] a, int left, int right) {
            
        if(left<right){
                
            int middle = (left+right)/2;
                
            //对左边进行递归

            mergeSort(a, left, middle);
                
            //对右边进行递归

            mergeSort(a, middle+1, right);
                
            //合并

            merge(a,left,middle,right);
        }
            
        return a;
    }

    private static void merge(int[] a, int left,int middle, int right) {
            
        int[] tmpArr = new int[a.length];
            
        int mid = middle+1; 
        //右边的起始位置

            
        int tmp = left;
            
        int third = left;
            
        while(left<=middle && mid<=right){
                
            //从两个数组中选取较小的数放入中间数组

                
            if(a[left]<=a[mid]){
                tmpArr[third++] = a[left++];
            }else{
                tmpArr[third++] = a[mid++];
            }
        }
            
        //将剩余的部分放入中间数组

            
        while(left<=middle){
            tmpArr[third++] = a[left++];
        }
            
        while(mid<=right){
            tmpArr[third++] = a[mid++];
        }
            
        //将中间数组复制回原数组

            
        while(tmp<=right){
            a[tmp] = tmpArr[tmp++];
        }
    }
        
    /************************基数排序************************/

        
    /**
        * 基数排序
        * @param array
        * @return
        */

        

    public static int[] radixSort(int[] a) {
            
        //找到最大数，确定要排序几趟

            
        int max = 0;
            
        for (int i = 0; i < a.length; i++) {
                
            if(a[i]<0) {
                System.out.println("只能比较正整数："+a[i]);
                    
                return a;
            }
                
            if(max<a[i]){
                max = a[i];
            }
        }
            
        //判断位数

            
        int times = 0;
            
        while(max>0){
            max = max/10;
            times++;
        }
            
        //建立十个队列

        List<ArrayList> queue = new ArrayList<ArrayList>();
            
        for (int i = 0; i < 10; i++) {
            ArrayList queue1 = new ArrayList();
            queue.add(queue1);
        }
            
        //进行times次分配和收集

            
        for (int i = 0; i < times; i++) {
                
            //分配

                
            for (int j = 0; j < a.length; j++) {
                    
                //每一次比较都把奇数相同的放在同一个队列中，Math.pow(a,b) a的b次方

                    
                //如果i=2表示取第二位，那么就%100取余 然后/10取取除

                    
                int x = a[j]%(int)Math.pow(10, i+1)/(int)Math.pow(10, i);
                    
                //直接去奇数位置寻找对应的列表

                ArrayList queue2 = queue.get(x);
                queue2.add(a[j]);
                queue.set(x,queue2);
            }
            //收集

                
            int count = 0;
                
            for (int j = 0; j < 10; j++) {
                    
                while(queue.get(j).size()>0){
                    ArrayList<Integer> queue3 = queue.get(j);
                    a[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }
            
        return a;
    }
        

    public static void main(String[] args) {
        System.out.println("-------------用对数器来测是算法的正确性------------");
        
        int testTims = 500000;
    //测试次数

        
        int maxSize = 20;
        //最大测试容量

        
        int maxNum = 100;
    //最大测试数据

        boolean euqals = true;
        
        for (int i = 0; i < testTims; i++) {
                //原始数组

               
                int[] arr1 = generate RandomArray(maxSize,maxNum);
               
                int[] arr2 = copyArray(arr1);
            
                //这两个数组除了

                int[] arr3 = copyArray(arr1);
               //这两个数组除了

               
                //数值相同内存地址完全没关系，请看copyArray()方法实现

               
               //insertSort(arr2);

               
               //divideInsertSort(arr2);

               
                //shellSort(arr2);

               
                //choiceSort(arr2);

               
                //heapSort(arr2);


                //bubbleSort(arr2);


                //quickSort(arr2);


                //mergeSort(arr2);


               //这个只能比较正整数

                radixSort(arr2);
                rightSort(arr3);
                //用java.util.Arrays包的排序算法排序

               
                if (!isEquals(arr2,arr3)) {
                    //比较是否相同

                    printArray(arr1);
                    //再次打印，程序员自己看看有没有毛病

                    printArray(arr2);//再次打印，程序员自己看看有没有毛病

                    printArray(arr3);//再次打印，程序员自己看看有没有毛病

                    euqals = false;//一旦有不一样的值就设为false;

                   
                    break;
                }
            }
            System.out.println(euqals ? "Success:恭喜你！没毛病！" : "Error：抱歉，有错误" );//没错就恭喜，有错就报错

    }
    
    //这是一个绝对正确但是复杂度不好的方法

    

    public static void rightSort(int[] arr) {
        Arrays.sort(arr);
    }
    
    //这是一个随机样本产生器

    

    public static int[] generateRandomArray(int maxSize, int maxNum) {
        
        int[] arr = new int[(int) ((maxSize+1) * Math.random())];
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*(maxNum+1)) - (int)(Math.random()*maxNum);
        }
        
        return arr;
    }
    
    //这个是对比算法a和b方法是否相同的方法

    

    public static boolean isEquals(int[] arr1, int[] arr2) {
        
       if (arr1.length != arr2.length) {
            
            return false;
        }
        
        if (arr1 != null && arr2 == null || arr1 == null && arr2 != null) {

            return false;
        }

        for (int i = 0; i < arr1.length; i++) {            
            if (arr1[i] != arr2[i]) {

                return false;
            }
        }

        return true;
    }
   
    //复制当前数组的一个样本

    

    public static int[] copyArray(int[] arr) {

        if (arr == null) {

            return null;
        }

       int[] newArray = new int[arr.length];
        
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = arr[i];
        }
        
        return newArray;
    }
    
    //打印数组

    

    public static void printArray(int[] arr) {      

        if(arr == null) {

            return;
        }
        
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}

