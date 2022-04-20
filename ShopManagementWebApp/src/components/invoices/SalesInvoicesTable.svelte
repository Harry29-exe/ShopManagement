<script lang="ts">
    import {SalesInvoiceDTO} from "../../dto/SalesInvoice";
    import Table from "../table/Table.svelte";

    const header: string[] = [
        "id", "has correction", "entity id",
        "issue date", "is payed", "details"
    ]
    export let invoices: SalesInvoiceDTO[] = [];

</script>

<div class="w-min-[320px] w-full bg-bg-200 v-stack mt-20 mx-auto py-4 rounded-md">
    <button class="center w-4/5 bg-bg-300 text-3xl btn-lg">+</button>
    <div class="table-wrapper w-4/5">
        <Table header={header}>
            <svelte:fragment slot="rows">
                {#each invoices as invoice}
                    <tr class="td">
                        <td>{invoice.id}</td>
                        <td>{!!invoice.correctionId}</td>
                        <td>{invoice.businessEntity.id}</td>
                        <td>{invoice.issueDate}</td>
                        <td>{invoice.isPayed}</td>
                    </tr>
                {/each}
            </svelte:fragment>
        </Table>
    </div>
</div>