import com.beancurd.rxjava.Dispose;
import com.beancurd.rxjava.Observable;
import com.beancurd.rxjava.Observer;
import com.beancurd.rxjava.observable.ObservableMap;
import com.beancurd.rxjava.observer.SimpleObserver;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        /**
         *  1. 我要实现 形如：
         *          Observable.just("1","2","3")
         *              .observe(new Observer {
         *                  ....
         *              })
         *
         *   2. 我要实现 形如：
         *           Observable.just("1","2","3")
         *              .map(mapper-> {})
         *              .observe(new Observer {
         *                  ....
         *              })
         *
         */

        // 1. 问题一 解决装箱问题，如果是int类型
        // 2. 问题二 Observer是应该使用类，还是接口呢？效率如何？
        // 3. 问题三 有没有哪个Observable 不必实现 subScribeActual方法， 这决定了是否使用abstract 关键字
        // 4. 问题四 Dispose 一定存在并发问题
        // 5. 问题五 Observer 失败、取消属于 onComplete 范围吗
        /*
            Integer [] a = new Integer[]{1, 2, 3, 4};
            Observable.just(a)
                    .observe(new SimpleObserver<Integer>() {
                        private Dispose mDispose;
                        @Override
                        public void onSubscribe(Dispose dispose) {
                            mDispose = dispose;
                            System.out.println("onSubscribe .... ");
                        }

                        @Override
                        public void onNext(Integer data) {
                            if (data == 3) {
                                mDispose.doCancel();
                            }
                            System.out.println("onNext .... " + data);
                        }

                        @Override
                        public void onError(Exception e) {
                            System.out.println("onError .... ");
                        }

                        @Override
                        public void onComplete() {
                            System.out.println("onComplete .... ");
                        }
                    });
        */


        // 6. 问题六 对于向Mapper这样的Observer，大多是透传，可以通过委托优化一下啊吗？ Kotlin的语言，真是太简洁了
        Integer [] a = new Integer[]{1, 2, 3, 4};
        Observable.just(a)
                .map(new ObservableMap.Mapper<Integer, String>() {
                    @Override
                    public String map(Integer rawData) {
                        return rawData+",beancurdV";
                    }
                })
                .observe(new SimpleObserver<String>() {
                    private Dispose mDispose;
                    @Override
                    public void onSubscribe(Dispose dispose) {
                        mDispose = dispose;
                        System.out.println("onSubscribe .... ");
                    }

                    @Override
                    public void onNext(String data) {
                        System.out.println("onNext .... " + data);
                    }

                    @Override
                    public void onError(Exception e) {
                        System.out.println("onError .... ");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete .... ");
                    }
                });
    }
}