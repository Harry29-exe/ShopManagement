<script lang="ts">
    import Table from "../../components/table/Table.svelte";
    import {onMount} from "svelte";
    import {SalesInvoiceClient} from "../../apiclient/SalesInvoiceClient";
    import {SalesInvoiceDTO} from "../../dto/SalesInvoice";
    import {popupStore} from "../../stores/PopupStore";
    import {TableHeader} from "../../components/table/TableHeader";

    const header: TableHeader<SalesInvoiceDTO> = TableHeader.fromArray<SalesInvoiceDTO>([
        ["Invoice id", inv => inv.id],
        ["Invoice correction", inv => inv.correctionId],
        ["Contractor name", inv => inv.businessEntity.entityName],
        ["Issue date", inv => inv.issueDate],
        ["Is payed", inv => inv.isPayed]
    ], inv => console.log(inv));
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

<div class="w-11/12 h-stack mx-auto">

    <div class="w-3/5">
        <Table header={header} values={invoiceList}/>
<!--        <SalesInvoicesTable invoices={invoiceList}/>-->
    </div>



</div>