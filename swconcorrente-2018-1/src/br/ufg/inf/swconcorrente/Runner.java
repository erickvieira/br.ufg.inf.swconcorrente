package br.ufg.inf.swconcorrente;

//import br.ufg.inf.swconcorrente.mainPainel.ui.controller.MainPanelController;

public class Runner {

    private static StuffPrinter o = new StuffPrinter("o", 100000000);
    private static StuffPrinter p = new StuffPrinter(".", 100000000);
    private static StuffPrinter x = new StuffPrinter("x", 100000000);
    private static int count = 0;

    private synchronized static void incrementCount() {
        if (count % 93 == 1 && count != 0) {
            System.out.println();
        }
        count++;
    }

    public static void main(String[] args) {
        long begin = System.nanoTime();
        // new MainPanelController().showMainPainel();
        o.start();
        p.start();
        x.start();

        try {
            o.join();
            p.join();
            x.join();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        long end = System.nanoTime();

        System.out.print("\n\nFIM\n" +
                "TEMPO: " + (
                        (((end - begin) / 1000000000.0) >= 1) ?
                                ((end - begin) / 1000000000.0) + "s" :
                                ((end - begin) / 1000000.0) + "ms"
                ) + "\n"
        );
    }

    private static class StuffPrinter extends Thread {
        private final String STUFF;
        private final int LIMIT;

        private StuffPrinter(final String STUFF, final int LIMIT) {
            this.STUFF = STUFF;
            this.LIMIT = LIMIT;
        }

        @Override
        public void run() {
            for (int i = 0; i < LIMIT; i++) {
                System.out.print(this.STUFF);
                incrementCount();
            }
        }

    }

}
