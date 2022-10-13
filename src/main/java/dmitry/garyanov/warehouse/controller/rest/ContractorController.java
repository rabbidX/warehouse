package dmitry.garyanov.warehouse.controller.rest;

import dmitry.garyanov.warehouse.model.Contractor;
import dmitry.garyanov.warehouse.service.ContractorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/contractors/")
@AllArgsConstructor
public class ContractorController {

    private ContractorService contractorService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contractor> getContractor(@PathVariable("id") Long contractorId) {
        if (contractorId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Contractor contractor = this.contractorService.getById(contractorId);

        if (contractor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(contractor, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contractor> saveContractor(@RequestBody @Validated Contractor contractor) {
        HttpHeaders headers = new HttpHeaders();

        if (contractor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        contractorService.save(contractor);
        return new ResponseEntity<>(contractor, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contractor> updateContractor(@RequestBody @Validated  Contractor contractor) {
        HttpHeaders headers = new HttpHeaders();

        if (contractor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        contractorService.save(contractor);
        return new ResponseEntity<>(contractor, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contractor>  delete(@PathVariable("id")Long contractorId) {
        if (contractorId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Contractor contractor = contractorService.getById(contractorId);
        if (contractor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        contractorService.delete(contractorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contractor>> getAlContractors() {
        List<Contractor> contractors = contractorService.getAll();
        if (contractors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(contractors, HttpStatus.OK);

    }
}
