using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using MyPrikormWebAPI.Repositories;
using MyPrikormWebAPI.DB.Entities;
using NLog;

namespace MyPrikormWebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PrikormListsController : ControllerBase
    {
        private PrikormListRepository rep;

        public PrikormListsController(PrikormListRepository rep)
        {
            this.rep = rep;
        }

        // GET api/prikormlist
        //[EnableCors("AnotherPolicy")]
        [HttpGet]
        public async Task<ActionResult<List<PrikormList>>> Get()
        {
            return await rep.GetAll();
        }

        // GET api/values/5
        //[EnableCors("AnotherPolicy")]
        [HttpGet("{id}")]
        public async Task<ActionResult<PrikormList>> Get(int id)
        {
            PrikormList prikormlist = await rep.Get(id);
            if (prikormlist == null)
            {
                return NotFound();
            }
            return new ObjectResult(prikormlist);
        }

        // POST api/values
        //[EnableCors("AnotherPolicy")]
        [HttpPost]
        public async Task<ActionResult<PrikormList>> Post(PrikormList prikormlist)
        {
            if (prikormlist == null)
            {
                return BadRequest();
            }

            return Ok(await rep.Create(prikormlist));
        }

        // PUT api/values/5
        //[EnableCors("AnotherPolicy")]
        [HttpPut("{id}")]
        public async Task<ActionResult<PrikormList>> Put(long id, PrikormList prikormlist)
        {
            if (prikormlist == null)
            {
                return BadRequest();
            }

            return Ok(await rep.Update(prikormlist));
        }

        // DELETE api/values/5
        //[EnableCors("AnotherPolicy")]
        [HttpDelete("{id}")]
        public async Task<ActionResult<PrikormList>> Delete(int id)
        {
            PrikormList prikormlist = await rep.Delete(id);
            if (prikormlist == null)
            {
                return NotFound();
            }
            return new ObjectResult(prikormlist);
        }
    }
}
