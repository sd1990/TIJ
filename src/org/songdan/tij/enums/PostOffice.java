package org.songdan.tij.enums;

import java.util.Iterator;

/**
 * 使用enum的职责链
 * 邮局需要以尽可能通用的方式处理每一封邮件，并且不断地尝试处理邮件，知道该邮件最终被确定为一封死信
 * Created by PC on 2016/4/5.
 */
public class PostOffice {

    enum Mail_Handler {
        GENERAL_DELIVER {
            @Override
            boolean handle(Mail mail) {
                switch (mail.generalDelivery) {
                    case YES:
                        System.out.println("Using general delivery for "+mail);
                        return true;
                    default:
                        return false;

                }
            }
        },MACHINE_SCAN {
            @Override
            boolean handle(Mail mail) {
                switch (mail.scannability) {
                    case UNSCANNABILITY:
                        return false;
                    default:
                        System.out.println("Deliverying "+mail+" automatically");
                        return true;
                }
            }
        },VISUAL_INSPECTION {
            @Override
            boolean handle(Mail mail) {
                switch (mail.readability) {
                    case ILLEGBLE:
                        return false;
                    default:
                        System.out.println("Deliverying "+mail+" normally");
                        return true;
                }
            }
        },RETURN_TO_SENDER {
            @Override
            boolean handle(Mail mail) {
                switch (mail.returnAddress) {
                    case MISSING:
                        return false;
                    default:
                        System.out.println("returning "+mail+" to sender");
                        return true;
                }
            }
        };
        abstract boolean handle(Mail mail);
    }

    static void handle(Mail m){
        for (Mail_Handler mailHandler : Mail_Handler.values()) {
            if (mailHandler.handle(m)) {
                return;
            }
        }
        System.out.println(m+"is a dead mail");
    }

    public static void main(String[] args) {
        for (Mail mail : Mail.generator(100)) {
            System.out.println(mail.detail());
            handle(mail);
            System.out.println("***************************");
        }
    }
}

class Mail {

    enum GeneralDelivery {
        YES, NO1, NO, NO3, NO4, NO5;
    }

    enum Scannability {
        UNSCANNABILITY, YES1, YES2, YES3, YES4;
    }

    enum Readability {
        ILLEGBLE, YES1, YES2, YES3, YES4;
    }

    enum Address {
        INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6;
    }

    enum ReturnAddress {
        MISSING, OK1, OK2, OK3, OK4, OK5;
    }

    GeneralDelivery generalDelivery;

    Scannability scannability;

    Readability readability;

    Address address;

    ReturnAddress returnAddress;

    static long counter = 0;

    long id = counter++;

    @Override
    public String toString() {
        return "Mail{" +
                "id=" + id +
                '}';
    }

    public String detail() {
        return toString() + " ,General Delivery : " + generalDelivery + " , Address Scanability : " + scannability
                + " , Address Readability : " + scannability + " , Address address : " + address + " , Return Address : "
                + returnAddress;
    }

    public static Mail randomMail() {
        Mail mail = new Mail();
        mail.generalDelivery = Enums.random(GeneralDelivery.class);
        mail.scannability = Enums.random(Scannability.class);
        mail.readability = Enums.random(Readability.class);
        mail.address = Enums.random(Address.class);
        mail.returnAddress = Enums.random(ReturnAddress.class);
        return mail;
    }

    public static Iterable<Mail> generator(final int count){
        return new Iterable<Mail>() {

            @Override
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {

                    private int n = 0;

                    @Override
                    public boolean hasNext() {
                        return n++ < count;
                    }

                    @Override
                    public Mail next() {
                        return randomMail();
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

}
