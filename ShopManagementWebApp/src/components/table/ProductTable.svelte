<script lang="ts">
    import {TableHeader} from "./TableHeader";
    import Table from "./Table.svelte";

    export interface Product {
        nameOnInvoice: string;
        itemId: number;
        quantity: number;
        price: number;
        discountPercentage: number
        taxRate: number;
    }

    export let products: Product[] = [];

    const tHeader: TableHeader<Product> = TableHeader.fromArray([
        ["Item id", p => p.itemId],
        ["Item name", p => p.nameOnInvoice],
        ["Price", p => p.price],
        ["Quantity", p => p.quantity],
        ["Tax", p => p.taxRate],
        ["Discount", p => p.discountPercentage],
        ["Final price", p => (p.price *
                (1 - (p.discountPercentage ? p.discountPercentage : 0)))
            * (1 + p.taxRate) * p.quantity]
    ])
</script>

<Table header={tHeader} values={products}></Table>

