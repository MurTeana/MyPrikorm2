using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using MyPrikormWebAPI.Repositories;
using MyPrikormWebAPI.Model.DB.Entities;
using NLog;

namespace MyPrikormWebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PrikormListsController : ControllerBase
    {
        private PrikormListRepository prikormlistRepository;
        private static Logger logger = LogManager.GetCurrentClassLogger();
        public PrikormListsController(PrikormListRepository prikormlistRepository)
        {
            this.prikormlistRepository = prikormlistRepository;
        }

        [HttpGet]
        public async Task<ActionResult<List<PrikormList>>> Get()
        {
            try
            {
                return Ok(await prikormlistRepository.GetAll());
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<PrikormList>> Get(int id)
        {
            try
            {
                PrikormList prikormlist = await prikormlistRepository.Get(id);
                if (prikormlist == null)
                {
                    logger.Info("PrikormList is not found: " + id + ".");
                    return NotFound();
                }

                return Ok(prikormlist);
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpGet("x/{IdUser}")]
        public async Task<ActionResult<List<PrikormList>>> GetByIdUser(int IdUser)
        {
            try
            {
                List<PrikormList> prikormlist = await prikormlistRepository.GetByIdUser(IdUser);
                if (prikormlist == null)
                {
                    logger.Info("PrikormList is not found: " + IdUser + ".");
                    return NotFound();
                }

                return Ok(prikormlist);
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpPost]
        public ActionResult<PrikormList> Post(PrikormList prikormlist)
        {
            if (!ModelState.IsValid)
            {
                logger.Info("PrikormList ModelState is not valid.");
                return BadRequest();
            }

            try
            {
                return Ok(prikormlistRepository.Create(prikormlist));
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpPut("{id}")]
        public ActionResult<PrikormList> Put(long id, PrikormList prikormlist)
        {
            if (!ModelState.IsValid)
            {
                logger.Info("PrikormList ModelState is not valid.");
                return BadRequest();
            }

            try
            {
                return Ok(prikormlistRepository.Update(prikormlist));
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpDelete("{id}")]
        public ActionResult<PrikormList> Delete(int id)
        {
            try
            {
                PrikormList prikormlist = prikormlistRepository.Delete(id);
                if (prikormlist == null)
                {
                    logger.Info("PrikormList is not found: " + id + ".");
                    return NotFound();
                }

                return NoContent();
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }
    }
}
