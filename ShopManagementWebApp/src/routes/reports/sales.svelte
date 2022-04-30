<script lang="ts">
    import Table from "../../components/table/Table.svelte";
    import {TableHeader} from "../../components/table/TableHeader";
    import {SalesReportDTO} from "../../dto/SalesReportsDTOs";
    import {SalesReportsClient} from "../../apiclient/SalesReportsClient";
    import { popupStore} from "../../stores/PopupStore";

    const tHeader: TableHeader<SalesReportDTO> = TableHeader.fromArray([
        ["From date", report => (new Date(report.from)).toDateString()],
        ["To Date", report => (new Date(report.to)).toDateString()],
        ["Transactions made", report => report.data.transactions],
        ["Sales total value", report => report.data.salesTotal],
    ])

    let from: String;
    let to: String;
    let reports: SalesReportDTO[] = [];

    async function fetchReport() {
        console.log((new Date(from)).getTime())
        let result = await SalesReportsClient.getSalesReport(
            new Date(from),
            new Date(to)
        )

        if (result.ok && result.result) {
            reports.push(result.result)
            reports = reports
        } else {
            popupStore.setNew(result.msg)
        }
    }
</script>


<div class="widget-lg w-container-md v-stack px-[5%]">

    <p class="header-lg">Sales Report</p>

    <div class="h-stack mb-8">
        <div class="input-wrapper-sm">
            <p>From Date</p>
            <input bind:value={from} type="date"/>
        </div>
        <div class="input-wrapper-sm">
            <p>To Date</p>
            <input bind:value={to} type="date"/>
        </div>
    </div>

    <button on:click={fetchReport}
            class="btn-md">
        Show Results
    </button>

    <Table header={tHeader} values={reports}/>

</div>