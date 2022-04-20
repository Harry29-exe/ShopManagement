<script lang="ts">
    import {SalesInvoiceDTO} from "../../dto/SalesInvoice";
    import {CompanyInfoDTO} from "../../dto/CompanyInfo";
    import {onMount} from "svelte";
    import {CompanyInfoClient} from "../../apiclient/CompanyInfoClient";

    export let invoice: SalesInvoiceDTO;
    let companyInfo: CompanyInfoDTO;


    onMount(async () => {
        let result = await CompanyInfoClient.getInfo();
        if (result.ok && result.result) {
            companyInfo = result.result;
        }
    })
</script>

<div class="">

</div>