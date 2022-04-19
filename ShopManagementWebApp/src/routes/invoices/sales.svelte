<script lang="ts">
    import Table from "../../components/table/Table.svelte";
    import {onMount} from "svelte";
    import {SalesInvoiceClient} from "../../apiclient/SalesInvoiceClient";
    import {SalesInvoiceDTO} from "../../dto/SalesInvoice";
    import {popupStore} from "../../stores/PopupStore";
    let header: string[] = [
        "id", "has correction", "entity id",
        "issue date", "is payed", "details"
    ]
    let sampleValue: any[][] = [
        [1, false, 2, "31-03-2021", false],
        [2, false, 12, "31-03-2021", false],
        [3, true, 17, "31-03-2021", false],
        [4, false, 17, "31-03-2021", true],
    ]
    let invoiceList: SalesInvoiceDTO[] = [];

    onMount(async () => {
        let res = await SalesInvoiceClient.getAllSalesInvoices()

        if (res.ok) {
            console.log("ok")
            invoiceList = res.result
        } else {
            popupStore.setNew(res.msg)
        }
    })

</script>


<div class="w-min-[320px] w-11/12 bg-bg-200 v-stack mt-20 mx-auto py-4 rounded-md">
    <button class="center w-4/5 bg-bg-300 text-3xl btn-lg">+</button>

    <div class="table-wrapper w-4/5">
        <Table header={header} values={sampleValue}>
            <svelte:fragment slot="rows">
            {#each invoiceList as invoice}
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