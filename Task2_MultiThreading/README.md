Customers and Cashiers get generated using 
CustomerGenerator and CashierGenerator using the 
configuration data located in config.txt

config.txt:\
"amountOfCashiers"\
"amountOfCustomers" "fromRange" "toRange"

Each customer has a time field that will be generated 
in milliseconds using the [fromRange,toRange] range
that a cashier will need to prepareFood.

General Process of every thread(cashier):\
    - Adds a customer into its own Queue from UnOrderedCustomersList\
    - Removes from its queue a customer and adds into OrderedCustomersList (Thread will sleep here)\
    - Customers from OrderedCustomerList will served by a different cashier (Thread will sleep here)
