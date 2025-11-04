package week08.chandra.id.ac.umn;

import java.util.ArrayList;
import java.util.Scanner;

public class MainTugas {
	
	static ArrayList<Item> ListOfItems = new ArrayList<Item>();
	static ArrayList<PaymentTugas> ListOfPayments = new ArrayList<PaymentTugas>();
	static Scanner s = new Scanner(System.in);
	
	public static void seedData() {
		ListOfItems.add(new Item("Kulkas", "Elektronik", 4800000));
		ListOfItems.add(new Item("TV", "Elektronik", 1280000));
		ListOfItems.add(new Item("Laptop", "Komputer", 6000000));
		ListOfItems.add(new Item("PC", "Komputer", 12000000));
	}
	
	public static void printItem(Item item) {
		System.out.println("Nama :" + item.getName());
		System.out.println("Tipe :" + item.getType());
		System.out.println("Harga :" + item.getPrice());
	}
	
	public static void main(String[] args) {
		int opt = 0;
		int id = 0;
		seedData();
		
		do {
			System.out.println("---Program Toko Elektronik---");
			System.out.println("1. Pesan Barang");
			System.out.println("2. Lihat Pesanan");
			System.out.println("0. Keluar");
			System.out.print("Pilih: ");
			opt = s.nextInt();
			
			if(opt == 1) {
				System.out.println("---Daftar Barang---");
				for(int i=0; i<ListOfItems.size();i++) {
					System.out.println("No :" +(i+1));
					printItem(ListOfItems.get(i));
					System.out.println("-------------------------");
				}
				System.out.print("Pilih : ");
				id = s.nextInt();
				
				Item selectedItem = ListOfItems.get(id - 1); 
				
				System.out.println("---Tipe Pembayaran---");
				System.out.println("1. Cash");
				System.out.println("2. Credit");
				System.out.print("Pilih: ");
				int paymentType = s.nextInt();
				
				if(paymentType == 1) {
					Cash newPayment = new Cash(selectedItem);
					
					System.out.print("Bayar (1=Yes / 2=No): ");
					int payNow = s.nextInt();
					
					if(payNow == 1) {
						System.out.println("Harga Pembayaran: " + selectedItem.getPrice());
						System.out.print("Bayar: ");
						int amount = s.nextInt();
						
						if(amount == selectedItem.getPrice()) {
							newPayment.pay(); 
							System.out.println("Transaksi LUNAS.");
							ListOfPayments.add(newPayment); 
							System.out.println("Pesanan ditambahkan.");
						} else {
							System.out.println("Pembayaran kurang, transaksi dibatalkan.");
							continue; 
						}
					} else if (payNow == 2) { 
						System.out.println("Transaksi disimpan.");
						ListOfPayments.add(newPayment);
						System.out.println("Pesanan berhasil ditambahkan.");
					} else {
						continue;
					}
				}
				else if(paymentType == 2) {
					int maxInstallment;
					
					do {
						System.out.print("Lama cicilan (3/6/9/12): ");
						maxInstallment = s.nextInt();
					} while (maxInstallment != 3 && maxInstallment != 6 && maxInstallment != 9 && maxInstallment != 12);
					
					Credit newPayment = new Credit(selectedItem, maxInstallment);
					
					int firstInstallmentAmount = selectedItem.getPrice() / maxInstallment;
					System.out.println("Harga Pembayaran: " + firstInstallmentAmount);
					System.out.print("Bayar: ");
					int amount = s.nextInt();

					if(amount == firstInstallmentAmount) {
						newPayment.pay(); 
						System.out.println("Transaksi berhasil dibayar.");
					} else {
						System.out.println("Jumlah pembayaran tidak cukup.");
					}
					
					ListOfPayments.add(newPayment); 
					System.out.println("Pesanan berhasil ditambahkan.");
					
				} else { 
					System.out.println("Tipe pembayaran tidak valid.");
					continue;
				}
			
			} else if(opt == 2) {
				if(ListOfPayments.isEmpty()) {
					System.out.println("Belum ada pesanan.");
					continue;
				}
				
				System.out.println("---Daftar Pesanan---");
				for(int i=0; i<ListOfPayments.size(); i++) {
					PaymentTugas p = ListOfPayments.get(i);
					System.out.println("No: " + (i+1));
					System.out.println("Nama: " + p.getItemName());
					System.out.println("Tipe: " + p.getItem().getType());
					System.out.println("Status: " + p.getStatus());
					System.out.println("Sisa Pembayaran: " + p.getRemainingAmount());
					System.out.println("-------------------------");
				}
				
				System.out.print("Pilih No Transaksi: ");
				int orderId = s.nextInt();
				
				PaymentTugas selectedPayment = ListOfPayments.get(orderId - 1);
				
				if(selectedPayment.isPaidOff()) {
					System.out.println("Pesanan ini sudah LUNAS.");
					continue;
				}
				
				System.out.println("---Pembayaran Pesanan---");
				System.out.println("Nama Barang: " + selectedPayment.getItemName());
				System.out.println("Sisa Pembayaran: " + selectedPayment.getRemainingAmount());
				
				if(selectedPayment instanceof Cash) {
					System.out.print("Bayar ( " + selectedPayment.getRemainingAmount() + "): ");
					int amount = s.nextInt();
					if(amount == selectedPayment.getRemainingAmount()) {
						selectedPayment.pay(); 
						System.out.println("Pembayaran LUNAS.");
					} else {
						System.out.println("Pembayaran gagal, jumlah tidak sesuai.");
						continue;
					}
				} 
				else if (selectedPayment instanceof Credit) {
					System.out.print("Bayar (1=Yes / 2=No): ");
				    int confirmPay = s.nextInt();
				    
				    if(confirmPay == 1) { 
				        int amountPaid = selectedPayment.pay();
				        System.out.println("Pembayaran cicilan sebesar " + amountPaid + " berhasil.");
				        System.out.println("Status Baru: " + selectedPayment.getStatus());
				        System.out.println("Sisa Tagihan Baru: " + selectedPayment.getRemainingAmount());
				    } else if (confirmPay == 2) {
				        System.out.println("Pembayaran dibatalkan.");
				    } else {
				        System.out.println("Pilihan tidak valid.");
				        continue;
				    }
				}
			
			} else if(opt == 0) {
				System.out.println("Terima Kasih");
				
			} else { 
				continue;
			}
			
		} while(opt!=0);
		
		s.close(); 
	}
}