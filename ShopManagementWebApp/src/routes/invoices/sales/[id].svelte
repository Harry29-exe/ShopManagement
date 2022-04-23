<script lang="ts">
    import {page} from "$app/stores"
    import {SalesInvoiceClient} from "../../../apiclient/SalesInvoiceClient";
    import {SalesInvoiceDTO} from "../../../dto/SalesInvoiceDTOs";
    import {onMount} from "svelte";
    import {popupStore} from "../../../stores/PopupStore";
    import SalesInvoiceView from "../../../components/invoices/SalesInvoiceView.svelte";


    let invoiceId: number = Number.parseInt($page.params.id);
    let invoice: SalesInvoiceDTO;
    onMount(async () => {
        let result = await SalesInvoiceClient.getSalesInvoice(invoiceId);
        if (result.ok && result.result) {
            invoice = result.result;
        } else {
            popupStore.setNew(result.msg);
        }
    })
</script>

{#if invoice}
<div class="mt-10 w-11/12 lg:w-8/12 mx-auto">
    <SalesInvoiceView invoice={invoice}/>
</div>
{/if}