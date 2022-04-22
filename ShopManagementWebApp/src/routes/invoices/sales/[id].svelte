<script lang="ts">
    import {page} from "$app/stores"
    import {SalesInvoiceClient} from "../../../apiclient/SalesInvoiceClient";
    import {SalesInvoiceDTOs} from "../../../dto/SalesInvoiceDTOs";
    import {onMount} from "svelte";
    import {popupStore} from "../../../stores/PopupStore";
    import SalesInvoiceView from "../../../components/invoices/SalesInvoiceView.svelte";
    import BackButton from "../../../components/BackButton.svelte";


    let invoiceId: number = Number.parseInt($page.params.id);
    let invoice: SalesInvoiceDTOs;
    onMount(async () => {
        let result = await SalesInvoiceClient.getSalesInvoice(invoiceId);
        if (result.ok && result.result) {
            invoice = result.result;
        } else {
            popupStore.setNew(result.msg);
        }
    })
</script>

<BackButton/>
{#if invoice}
<div class="mt-10 w-11/12 lg:w-8/12 mx-auto">
    <SalesInvoiceView invoice={invoice}/>
</div>
{/if}