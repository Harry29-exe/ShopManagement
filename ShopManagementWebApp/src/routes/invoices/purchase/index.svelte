<script lang="ts">
    import {goto} from "$app/navigation";
    import Table from "../../../components/table/Table.svelte";
    import {TableHeader} from "../../../components/table/TableHeader";
    import {page} from "$app/stores";
    import {onMount} from "svelte";
    import {popupStore} from "../../../stores/PopupStore";
    import {PurchaseInvoiceClient} from "../../../apiclient/PurchaseInvoiceClient";
    import {PurchaseInvoiceDTO} from "../../../dto/PurchaseInvoiceDTOs";

    const header: TableHeader<PurchaseInvoiceDTO> = TableHeader.fromArray([
        ["Invoice id", inv => inv.id],
        ["Invoice correction", inv => inv.correctionId],
        ["Contractor name", inv => inv.contractor.entityName],
        ["Issue date", inv => inv.issueDate],
        ["Is payed", inv => inv.isPayed]
    ], inv => goto(`${$page.url}/${inv.id}`));
    let invoiceList: PurchaseInvoiceDTO[] = [];

    onMount(async () => {
        let res = await PurchaseInvoiceClient.getAll()

        if (res.ok) {
            console.log("ok")
            invoiceList = res.result
        } else {
            popupStore.setNew(res.msg)
        }
    })
</script>


<div class="widget-lg w-container-md w-11/12 v-stack mt-10">
    <div class="v-stack w-full md:w-11/12">

        <div class="text-4xl font-bold mb-10">Sales Invoices</div>

        <div class="center mb-6 border-2 border-black-alpha-400 rounded-md px-20 py-2">
            <button on:click={() => goto("/invoices/purchase/new")}
                    class="btn-lg bg-bg-50 w-80px"
            >
                +
            </button>
            <button class="btn-lg bg-bg-50 w-80px" disabled>
                ?
            </button>
        </div>


        <Table header={header} values={invoiceList}/>

    </div>
</div>
