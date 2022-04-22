<script lang="ts">
    import Table from "../../../components/table/Table.svelte";
    import {onMount} from "svelte";
    import {SalesInvoiceClient} from "../../../apiclient/SalesInvoiceClient";
    import {SalesInvoiceDTO} from "../../../dto/SalesInvoiceDTO";
    import {popupStore} from "../../../stores/PopupStore";
    import {TableHeader} from "../../../components/table/TableHeader";
    import {goto} from "$app/navigation";
    import {page} from "$app/stores";

    const header: TableHeader<SalesInvoiceDTO> = TableHeader.fromArray<SalesInvoiceDTO>([
        ["Invoice id", inv => inv.id],
        ["Invoice correction", inv => inv.correctionId],
        ["Contractor name", inv => inv.businessEntity.entityName],
        ["Issue date", inv => inv.issueDate],
        ["Is payed", inv => inv.isPayed]
    ], inv => goto(`${$page.url}/${inv.id}`));
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

<div class="w-11/12 center mx-auto mt-10">

    <Table header={header} values={invoiceList}/>

</div>